package com.zinoview.tzusersapp.presentation.di.module

import com.zinoview.tzusersapp.data.UsersRepository
import com.zinoview.tzusersapp.domain.MapperDataUserToDomain
import com.zinoview.tzusersapp.domain.MapperDataUsersToDomain
import com.zinoview.tzusersapp.domain.UsersInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideMapperDataUsersToDomain() : MapperDataUserToDomain {
        return MapperDataUserToDomain.Base()
    }

    @Provides
    fun provideUsersInteractor(usersRepository: UsersRepository,mapperDataUserToDomain: MapperDataUserToDomain) : UsersInteractor {
        return UsersInteractor.Base(
            usersRepository,
            MapperDataUsersToDomain.Base(
                mapperDataUserToDomain
            ),
            mapperDataUserToDomain
        )
    }
}