package com.zinoview.tzusersapp.core

interface BaseUser : Abstract.User {

    data class Test(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : BaseUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
                = mapper.map(id, email, firstName, lastName, avatar)
    }
}