package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.domain.DomainUsers
import com.zinoview.tzusersapp.presentation.core.log

sealed class UiUsers : Abstract.Users {

    class Success(
        private val users: List<BaseUser>
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    class Cache(
        private val users: List<BaseUser>
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.mapCache(users)
        }

    class Failure(
        private val message: String
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                = mapper.map(message)
    }
}