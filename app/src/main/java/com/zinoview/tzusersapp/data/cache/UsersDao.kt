package com.zinoview.tzusersapp.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<CacheUser.Base>)

    //for update user by primary key
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: CacheUser.Base)

    @Query("delete from users where email = :userEmail")
    suspend fun delete(userEmail: String)

    @Query("select * from users")
    fun users() : Flow<List<CacheUser.Base>>

    //for checking data base on empty
    @Query("select * from users")
    suspend fun emptyProbablyUsers() : Array<CacheUser.Base>

}