package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cache.CacheDataSource
import com.zinoview.tzusersapp.data.cache.CacheUser
import kotlinx.coroutines.flow.Flow

sealed class ModifyUser {

    abstract suspend fun execute(cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>)

    class Edit(
        private val baseUser: BaseUser
    ) : ModifyUser() {

        override suspend fun execute(cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>) {
            cacheDataSource.update(baseUser)
        }
    }

    class Delete(
        private val email: String
    ) : ModifyUser() {

        override suspend fun execute(cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>)
            = cacheDataSource.delete(email)
    }
}