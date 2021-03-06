package com.zinoview.tzusersapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzusersapp.core.Abstract

interface CloudUser : Abstract.User {

    class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("email")
        private val email: String,
        @SerializedName("first_name")
        private val firstName: String,
        @SerializedName("last_name")
        private val lastName: String,
        @SerializedName("avatar")
        private val avatar: String
    ) : CloudUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }
}