package com.zinoview.tzusersapp.core

interface Abstract {

    interface Mapper

    interface User {
        fun <T> map(mapper: UserMapper<T>) : T
    }

    interface UserMapper<T> : Mapper {
        fun map(id: Int,email: String,firstName: String,lastName: String,avatar: String) : T
    }

    interface Users {
        fun <T> map(mapper: UsersMapper<T>) : T
    }

    interface UsersMapper<T> : Mapper {

        fun map(users: List<BaseUser>) : T

        fun map(message: String) : T

        fun mapCache(users: List<BaseUser>) : T
    }
}