package com.zinoview.tzusersapp.presentation.di.module

import com.zinoview.tzusersapp.core.ResourceProvider
import com.zinoview.tzusersapp.data.ExceptionMapper
import com.zinoview.tzusersapp.data.MapperCloudUserToData
import com.zinoview.tzusersapp.data.UsersRepository
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideUsersRepository(cloudDataSource: CloudDataSource<CloudUser>,resourceProvider: ResourceProvider) : UsersRepository {
        return UsersRepository.Base(
            cloudDataSource,
            MapperCloudUserToData.Base(),
            ExceptionMapper.Base(resourceProvider)
        )
    }
}