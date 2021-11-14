package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

interface UiUser : BaseUser {

    data class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : UiUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }
}