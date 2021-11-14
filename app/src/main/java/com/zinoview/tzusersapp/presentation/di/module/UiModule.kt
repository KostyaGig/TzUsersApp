package com.zinoview.tzusersapp.presentation.di.module

import com.zinoview.tzusersapp.domain.UsersInteractor
import com.zinoview.tzusersapp.presentation.UsersViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class UiModule {

    @Provides
    fun provideDefaultCoroutineDispatcher() : CoroutineDispatcher
        = Dispatchers.IO

    @Provides
    fun provideUsersViewModelFactory(usersInteractor: UsersInteractor,coroutineDispatcher: CoroutineDispatcher) : UsersViewModelFactory {
        return UsersViewModelFactory.Base(
            usersInteractor,coroutineDispatcher
        )
    }
}