package com.zinoview.tzusersapp.presentation.di.module

import com.zinoview.tzusersapp.core.ResourceProvider
import com.zinoview.tzusersapp.data.ExceptionMapper
import com.zinoview.tzusersapp.data.MapperToDataUser
import com.zinoview.tzusersapp.data.UsersRepository
import com.zinoview.tzusersapp.data.cache.CacheDataSource
import com.zinoview.tzusersapp.data.cache.CacheUser
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.domain.MapperDataUserToDomain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Module(includes = [NetworkModule::class,CacheModule::class])
class DataModule {

    @Provides
    fun provideUsersRepository(cloudDataSource: CloudDataSource<CloudUser>, cacheDataSource: CacheDataSource<Flow<List<CacheUser>>>, resourceProvider: ResourceProvider) : UsersRepository {
        return UsersRepository.Base(
            cloudDataSource,
            cacheDataSource,
            MapperToDataUser.Base(),
            ExceptionMapper.Base(resourceProvider)
        )
    }
}