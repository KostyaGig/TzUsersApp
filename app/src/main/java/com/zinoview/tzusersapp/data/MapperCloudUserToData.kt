package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.Abstract

interface MapperCloudUserToData : Abstract.UserMapper<DataUser> {

    class Base : MapperCloudUserToData {
        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): DataUser
            = DataUser.Base(
                id, email, firstName, lastName, avatar
            )
    }
}