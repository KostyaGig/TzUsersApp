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
    fun provideUsersInteractor(usersRepository: UsersRepository) : UsersInteractor {
        return UsersInteractor.Base(
            usersRepository,
            MapperDataUsersToDomain.Base(
                MapperDataUserToDomain.Base()
            )
        )
    }
}