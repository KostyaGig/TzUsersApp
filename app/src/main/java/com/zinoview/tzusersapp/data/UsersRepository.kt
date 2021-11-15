package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cache.CacheDataSource
import com.zinoview.tzusersapp.data.cache.CacheUser
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.presentation.core.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface UsersRepository {

    suspend fun users() : Flow<DataUsers>

    class Base(
        private val cloudDataSource: CloudDataSource<CloudUser>,
        private val cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>,
        private val mapperToDataUser: MapperToDataUser,
        private val exceptionMapper: ExceptionMapper<String>
    ) : UsersRepository {

        override suspend fun users(): Flow<DataUsers> {
            if (cacheDataSource.isNotEmpty()) {
                log("Cache is not empty")
                return usersFromCache()
            }
            return try {
                log("Cache is empty")
                val cloudUsers = cloudDataSource.users()
                cacheDataSource.save(cloudUsers)
                val dataUsers = cloudUsers.map { cloudUser -> cloudUser.map(mapperToDataUser)  }
                flow {
                    emit(
                        DataUsers.Success(dataUsers)
                    )
                }
            } catch (e: Exception) {
                val errorMessage = exceptionMapper.map(e)
                return flow {
                    emit(
                        DataUsers.Failure(errorMessage)
                    )
                }
            }
        }

        private suspend fun usersFromCache() : Flow<DataUsers> = suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                cacheDataSource.users().collect { cacheUsers ->
                    val dataUsers = cacheUsers.map { cacheUser -> cacheUser.map(mapperToDataUser) }
                    continuation.resume(
                        flow {
                            emit(DataUsers.Cache(dataUsers))
                        }
                    )
                }
            }
        }
    }

    class Test(
        private val cacheDataSource: CacheDataSource<List<BaseUser>>,
        private val cloudDataSource: CloudDataSource<BaseUser>
    ) : UsersRepository {

        private var count = 0

        override suspend fun users(): Flow<DataUsers> {
            if (cacheDataSource.isNotEmpty()) {
                return flow {
                    emit(DataUsers.Cache(cacheDataSource.users()))
                }
            }
            val baseUsers = cloudDataSource.users()
            val result = if (count % 2 == 1) {
                cacheDataSource.save(listOf())
                flow {
                    emit(
                        DataUsers.Success(
                            baseUsers
                        )
                    )
                }
            } else {
                flow {
                    emit(
                        DataUsers.Failure(
                            "No connection"
                        )
                    )
                }
            }
            count++
            return result
        }

    }
}