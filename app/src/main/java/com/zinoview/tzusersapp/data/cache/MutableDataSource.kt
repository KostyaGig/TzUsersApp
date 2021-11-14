package com.zinoview.tzusersapp.data.cache

import com.zinoview.tzusersapp.data.core.DataSource

interface MutableDataSource<S,R> : DataSource<R> {

    suspend fun save(data: S)
}