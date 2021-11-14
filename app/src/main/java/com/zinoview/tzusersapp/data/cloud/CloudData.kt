package com.zinoview.tzusersapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzusersapp.presentation.core.log

interface CloudData {

    fun users() : List<CloudUser>

    class Base(
        @SerializedName("data")
        private val users: List<CloudUser.Base>
//    @SerializedName("page")
//    private val page: Int
    ) : CloudData {

        override fun users(): List<CloudUser> {
//            log("page $page")
            return users
        }
    }

}