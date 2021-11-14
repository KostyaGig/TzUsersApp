package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

sealed class UiUsers : Abstract.Users {

    class Success(
        private val users: List<BaseUser>
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    class Failure(
        private val message: String
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
    }
}