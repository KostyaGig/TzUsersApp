package com.zinoview.tzusersapp.data.cloud

import com.google.gson.annotations.SerializedName

interface CloudUser {

    class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("email")
        val email: String,
        @SerializedName("first_name")
        private val firstName: String,
        @SerializedName("last_name")
        private val lastName: String,
        @SerializedName("avatar")
        private val avatar: String
    ) : CloudUser {

    }
}