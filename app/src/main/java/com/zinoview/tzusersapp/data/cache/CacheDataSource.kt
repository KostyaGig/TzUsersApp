package com.zinoview.tzusersapp.data.cache

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cloud.CloudUser
import kotlinx.coroutines.flow.Flow

interface CacheDataSource<T> : MutableDataSource<List<CloudUser>,T> {

    suspend fun isNotEmpty() : Boolean

    class Base(
        private val database: DataBase.Room,
        private val mapperCloudUserToCache: MapperCloudUserToCache
        ) : CacheDataSource<Flow<List<CacheUser>>> {

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

    class Test : CacheDataSource<List<BaseUser>> {

        private var isNotEmpty = false

        override suspend fun isNotEmpty(): Boolean {
            return isNotEmpty
        }

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

        override suspend fun save(data: List<CloudUser>) {
            isNotEmpty = true
        }
    }
}