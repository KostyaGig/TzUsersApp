package com.zinoview.tzusersapp.data.cache

import com.zinoview.tzusersapp.data.cloud.CloudUser
import kotlinx.coroutines.flow.Flow

interface CacheDataSource : MutableDataSource<List<CloudUser>,Flow<List<CacheUser>>> {

    suspend fun isNotEmpty() : Boolean

    class Base(
        private val database: DataBase.Room,
        private val mapperCloudUserToCache: MapperCloudUserToCache
        ) : CacheDataSource {

        override suspend fun users(): Flow<List<CacheUser>> {
            return database.users()
        }

        override suspend fun save(data: List<CloudUser>) {
            val cacheUsers = data.map { cloudUser -> cloudUser.map(mapperCloudUserToCache) }
            database.save(cacheUsers)
        }

        override suspend fun isNotEmpty(): Boolean
            = database.usersIsEmpty().not()
    }
}