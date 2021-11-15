package com.zinoview.tzusersapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinoview.tzusersapp.domain.UsersInteractor
import com.zinoview.tzusersapp.presentation.core.Observe
import com.zinoview.tzusersapp.presentation.state.CommunicationUiStateUser
import com.zinoview.tzusersapp.presentation.state.MapperUiUserToCacheState
import com.zinoview.tzusersapp.presentation.state.MapperUiUsersToState
import com.zinoview.tzusersapp.presentation.state.UiStateUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

interface UsersViewModel : Observe<List<UiStateUser>> {

    fun users()

    fun modifyUser(modifyUserAction: ModifyUser)

    class Base(
        private val usersInteractor: UsersInteractor,
        private val mapperDomainUsersToUi: MapperDomainUsersToUi,
        private val mapperDomainUserToUi: MapperDomainUserToUi,
        private val mapperUiUsersToState: MapperUiUsersToState,
        private val cacheStateMapper: MapperUiUserToCacheState,
        private val communicationUiStateUser: CommunicationUiStateUser,
        private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : UsersViewModel, ViewModel() {

        override fun users() {
            communicationUiStateUser.postValue(listOf(UiStateUser.Progress))
            viewModelScope.launch(defaultDispatcher) {
                val domainUsers = usersInteractor.usersFromCache()
                val uiUsers = domainUsers.map { domain -> domain.map { domainUser ->
                        domainUser.map(mapperDomainUserToUi)
                    }
                }
                val uiStateUser = uiUsers.map { ui -> ui.map { uiUser ->
                        uiUser.map(cacheStateMapper)
                     }
                }
                withContext(Dispatchers.Main) {
                    uiStateUser.collect { uiStateUser ->
                        if (uiStateUser.isEmpty()) {
                            usersFromRemote()
                            cancel()
                        } else {
                            communicationUiStateUser.postValue(uiStateUser)
                        }
                    }
                }
            }
        }

        private fun usersFromRemote() {
            communicationUiStateUser.postValue(listOf(UiStateUser.Progress))
            viewModelScope.launch(defaultDispatcher) {
                val domainUsers = usersInteractor.users()
                val uiUsers = domainUsers.map { domain -> domain.map(mapperDomainUsersToUi) }
                val uiStateUser = uiUsers.map { ui -> ui.map(mapperUiUsersToState) }

                withContext(Dispatchers.Main) {
                    uiStateUser.collect { uiStateUser ->
                        communicationUiStateUser.postValue(uiStateUser)
                    }
                }
            }
        }

        override fun modifyUser(modifyUserAction: ModifyUser) {
            viewModelScope.launch(defaultDispatcher) {
                usersInteractor.modifyUser(modifyUserAction)
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiStateUser>>)
            = communicationUiStateUser.observe(owner,observer)

    }
}