package com.zinoview.tzusersapp.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.zinoview.tzusersapp.data.cache.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Module
class CacheModule {

    private companion object {
        private const val DATABASE_NAME = "users.db"
    }

    @Provides
    fun provideRoomDatabase(context: Context) : UsersDatabase {
        return Room
                .databaseBuilder(context,UsersDatabase::class.java, DATABASE_NAME)
                .build()
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase) : UsersDao {
        return usersDatabase.usersDao()
    }

    @Provides
    fun provideCacheDataSource(usersDao: UsersDao) : CacheDataSource<Flow<List<CacheUser>>> {
        return CacheDataSource.Base(
            DataBase.Room.Base(
                usersDao
            ),
            MapperCloudUserToCache.Base()
        )
    }
}