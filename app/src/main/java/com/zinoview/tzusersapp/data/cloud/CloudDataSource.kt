package com.zinoview.tzusersapp.data.cloud

import android.util.Log
import com.zinoview.tzusersapp.presentation.core.log

interface CloudDataSource {

    suspend fun users() : List<CloudUser>

    class Base(
        private val cloudUserService: CloudUserService
    ) : CloudDataSource {

        override suspend fun users(): List<CloudUser> {
            Log.d("zinoviewk","uses()")
            val cloudUsers = cloudUserService.data().users()
            Log.d("zinoviewk","User from network size ${cloudUsers.size}, email ${(cloudUsers[0] as CloudUser.Base).email}")
            return cloudUsers
        }
    }
}