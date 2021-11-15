package com.zinoview.tzusersapp.domain

import com.zinoview.tzusersapp.data.UsersRepository
import com.zinoview.tzusersapp.presentation.ModifyUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UsersInteractor {

    suspend fun users() : Flow<DomainUsers>

    suspend fun usersFromCache() : Flow<List<DomainUser>>

    suspend fun modifyUser(modifyUserAction: ModifyUser)

    class Base(
        private val repository: UsersRepository,
        private val mapperDataUsersToDomain: MapperDataUsersToDomain,
        private val mapperDataUserToDomain: MapperDataUserToDomain
    ) : UsersInteractor {

        override suspend fun users(): Flow<DomainUsers> {
            val dataUsers = repository.users()
            return dataUsers.map { dataUser -> dataUser.map(mapperDataUsersToDomain) }
        }

        override suspend fun usersFromCache(): Flow<List<DomainUser>> {
            return repository.usersFromCache().map { dataUsers ->
                dataUsers.reversed().map { dataUser ->
                    dataUser.map(mapperDataUserToDomain)
                }
            }
        }

        override suspend fun modifyUser(modifyUserAction: ModifyUser)
            = repository.modifyUser(modifyUserAction)
    }
}