package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

interface UsersRepository {

    suspend fun users() : Flow<DataUsers>

    class Base(
        private val cloudDataSource: CloudDataSource<CloudUser>,
        private val mapperCloudUserToData: MapperCloudUserToData,
        private val exceptionMapper: ExceptionMapper<String>
    ) : UsersRepository {

        override suspend fun users(): Flow<DataUsers> {
            return try {
                val cloudUsers = cloudDataSource.users()
                val dataUsers = cloudUsers.map { cloudUser -> cloudUser.map(mapperCloudUserToData)  }
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
    }

    class Test(
        private val cloudDataSource: CloudDataSource<BaseUser>
    ) : UsersRepository {

        private var count = 1

        override suspend fun users(): Flow<DataUsers> {
            val baseUsers = cloudDataSource.users()
            val result = if (count % 2 == 1) {
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