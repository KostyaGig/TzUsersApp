package com.zinoview.tzusersapp.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DataBase<T> {

    suspend fun users() : T

    interface Room : DataBase<Flow<List<CacheUser>>> {
        suspend fun usersIsEmpty(): Boolean

        suspend fun save(users: List<CacheUser>)

        suspend fun delete(userEmail: String)

        suspend fun update(cacheUser: CacheUser)

        class Base(
            private val usersDao: UsersDao
        ) : Room {

            override suspend fun users(): Flow<List<CacheUser>>
                = usersDao.users().map { array -> array.toList() }

            override suspend fun save(users: List<CacheUser>)
                = usersDao.insert(users as List<CacheUser.Base>)

            override suspend fun delete(userEmail: String)
                = usersDao.delete(userEmail)

            override suspend fun update(cacheUser: CacheUser)
                = usersDao.update(cacheUser as CacheUser.Base)

            override suspend fun usersIsEmpty(): Boolean
                = usersDao.emptyProbablyUsers().isEmpty()
        }
    }
}