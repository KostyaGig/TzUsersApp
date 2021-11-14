package com.zinoview.tzusersapp.domain

import com.zinoview.tzusersapp.core.Abstract

interface MapperDataUserToDomain : Abstract.UserMapper<DomainUser> {

    class Base : MapperDataUserToDomain {
        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): DomainUser
            = DomainUser.Base(
                id, email, firstName, lastName, avatar
            )
    }
}