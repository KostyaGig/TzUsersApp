package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cache.CacheDataSource
import com.zinoview.tzusersapp.data.cache.CacheUser
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.presentation.ModifyUser
import com.zinoview.tzusersapp.presentation.core.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception

interface UsersRepository {

    suspend fun users() : Flow<DataUsers>

    suspend fun usersFromCache() : Flow<List<DataUser>>

    suspend fun modifyUser(modifyUserAction: ModifyUser)

    class Base(
        private val cloudDataSource: CloudDataSource<CloudUser>,
        private val cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>,
        private val mapperToDataUser: MapperToDataUser,
        private val exceptionMapper: ExceptionMapper<String>
    ) : UsersRepository {

        override suspend fun users(): Flow<DataUsers> {
            delay(DELAY.toLong())
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

        override suspend fun usersFromCache(): Flow<List<DataUser>> {
            delay(DELAY.toLong())
            return cacheDataSource.users().map { cacheUsers ->
                cacheUsers.map { cacheUser ->
                        cacheUser.map(mapperToDataUser)
                    }
            }
        }

        override suspend fun modifyUser(modifyUserAction: ModifyUser) {
            modifyUserAction.execute(cacheDataSource)
        }

        private companion object {
            private const val DELAY = 2000
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

        override suspend fun usersFromCache(): Flow<List<DataUser>> = flow { }

        override suspend fun modifyUser(modifyUserAction: ModifyUser) = Unit
    }
}
