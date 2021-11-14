package com.zinoview.tzusersapp.domain

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

sealed class DomainUsers : Abstract.Users {

    class Success(
        private val users: List<BaseUser>
    ) : DomainUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    class Failure(
        private val message: String
    ) : DomainUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
    }
}