package com.zinoview.tzusersapp.domain

import com.zinoview.tzusersapp.data.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UsersInteractor {

    suspend fun users() : Flow<DomainUsers>

    class Base(
        private val repository: UsersRepository,
        private val mapperDataUsersToDomain: MapperDataUsersToDomain
    ) : UsersInteractor {

        override suspend fun users(): Flow<DomainUsers> {
            val dataUsers = repository.users()
            return dataUsers.map { dataUser -> dataUser.map(mapperDataUsersToDomain) }
        }
    }
}