package com.zinoview.tzusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zinoview.tzusersapp.domain.UsersInteractor
import com.zinoview.tzusersapp.presentation.state.CommunicationUiStateUser
import com.zinoview.tzusersapp.presentation.state.MapperUiUserToCacheState
import com.zinoview.tzusersapp.presentation.state.MapperUiUserToCommonState
import com.zinoview.tzusersapp.presentation.state.MapperUiUsersToState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface UsersViewModelFactory : ViewModelProvider.Factory {

    class Base(
        private val usersInteractor: UsersInteractor,
        private val defaultCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : UsersViewModelFactory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val cacheStateMapper = MapperUiUserToCacheState.Base()
            return UsersViewModel.Base(
                usersInteractor,
                MapperDomainUsersToUi.Base(
                    MapperDomainUserToUi.Base()
                ),
                MapperDomainUserToUi.Base(),
                MapperUiUsersToState.Base(
                    MapperUiUserToCommonState.Base(),
                    cacheStateMapper
                ),
                cacheStateMapper,
                CommunicationUiStateUser.Base()
            ) as T
        }

    }
}