package com.zinoview.tzusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zinoview.tzusersapp.domain.UsersInteractor
import com.zinoview.tzusersapp.presentation.state.CommunicationUiStateUser
import com.zinoview.tzusersapp.presentation.state.MapperUiUserToState
import com.zinoview.tzusersapp.presentation.state.MapperUiUsersToState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface UsersViewModelFactory : ViewModelProvider.Factory {

    class Base(
        private val usersInteractor: UsersInteractor,
        private val defaultCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : UsersViewModelFactory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = UsersViewModel.Base(
                usersInteractor,
                MapperDomainUsersToUi.Base(
                    MapperDomainUserToUi.Base()
                ),
                MapperUiUsersToState.Base(
                    MapperUiUserToState.Base()
                ),
                CommunicationUiStateUser.Base(),
                defaultCoroutineDispatcher
            ) as T

    }
}