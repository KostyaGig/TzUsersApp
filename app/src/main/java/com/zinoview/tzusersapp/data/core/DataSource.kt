package com.zinoview.tzusersapp.data.core

interface DataSource<T> {

    suspend fun users() : T
}