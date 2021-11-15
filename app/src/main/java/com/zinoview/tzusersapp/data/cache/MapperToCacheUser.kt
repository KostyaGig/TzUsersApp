package com.zinoview.tzusersapp.data.cache

import com.zinoview.tzusersapp.core.Abstract

interface MapperToCacheUser : Abstract.UserMapper<CacheUser> {

    class Base : MapperToCacheUser {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): CacheUser
            = CacheUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}