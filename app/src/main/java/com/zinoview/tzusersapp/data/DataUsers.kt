package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

sealed class DataUsers : Abstract.Users {

    data class Success(
        private val users: List<BaseUser>
    ) : DataUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    data class Failure(
        private val message: String
    ) : DataUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
    }
}