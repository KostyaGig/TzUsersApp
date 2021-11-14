package com.zinoview.tzusersapp.domain

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

interface MapperDataUsersToDomain : Abstract.UsersMapper<DomainUsers> {

    class Base(
        private val mapperDataUserToDomain: MapperDataUserToDomain
    ) : MapperDataUsersToDomain {

        override fun map(users: List<BaseUser>): DomainUsers
            = DomainUsers.Success(users.map { dataUser ->
                dataUser.map(mapperDataUserToDomain)
            })

        override fun map(message: String): DomainUsers
            = DomainUsers.Failure(message)

    }
}