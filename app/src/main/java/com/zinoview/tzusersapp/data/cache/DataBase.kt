package com.zinoview.tzusersapp.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

interface DataBase<T> {

    suspend fun users() : T

    interface Room : DataBase<Flow<List<CacheUser>>> {
        suspend fun usersIsEmpty(): Boolean

        suspend fun save(users: List<CacheUser>)

        class Base(
            private val usersDao: UsersDao
        ) : Room {

            override suspend fun users(): Flow<List<CacheUser>>
                = usersDao.users().map { array -> array.toList() }

            override suspend fun save(users: List<CacheUser>)
                = usersDao.insert(users as List<CacheUser.Base>)

            override suspend fun usersIsEmpty(): Boolean
                = usersDao.emptyProbablyUsers().isEmpty()
        }
    }
}