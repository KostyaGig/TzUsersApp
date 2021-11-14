package com.zinoview.tzusersapp.data.cache

import com.zinoview.tzusersapp.core.Abstract

interface MapperCloudUserToCache : Abstract.UserMapper<CacheUser> {

    class Base : MapperCloudUserToCache {

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