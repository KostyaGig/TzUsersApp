package com.zinoview.tzusersapp.data.cloud

import com.zinoview.tzusersapp.core.BaseUser

interface CloudDataSource<T> {

    suspend fun users() : List<T>

    class Base(
        private val cloudUserService: CloudUserService
    ) : CloudDataSource<CloudUser> {

        override suspend fun users(): List<CloudUser> {
            return cloudUserService.data().users()
        }
    }

    class Test : CloudDataSource<BaseUser> {

        override suspend fun users(): List<BaseUser> {
            return listOf(
                BaseUser.Test(
                    1,"testuser@mail.ru","Kostya","Brob","avatar.jpg"
                ),
                BaseUser.Test(
                    2,"test2user@mail.ru","Minor","Fromik","avatar2.jpg"
                ),
                BaseUser.Test(
                    3,"test3user@mail.ru","Feodora","Mint","avatar3.jpg"
                )
            )
        }
    }
}