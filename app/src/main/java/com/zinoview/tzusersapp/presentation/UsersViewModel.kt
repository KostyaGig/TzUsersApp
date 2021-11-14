package com.zinoview.tzusersapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinoview.tzusersapp.domain.UsersInteractor
import com.zinoview.tzusersapp.presentation.core.Observe
import com.zinoview.tzusersapp.presentation.state.CommunicationUiStateUser
import com.zinoview.tzusersapp.presentation.state.MapperUiUsersToState
import com.zinoview.tzusersapp.presentation.state.UiStateUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface UsersViewModel : Observe<List<UiStateUser>> {

    fun users()

    class Base(
        private val usersInteractor: UsersInteractor,
        private val mapperDomainUsersToUi: MapperDomainUsersToUi,
        private val mapperUiUsersToState: MapperUiUsersToState,
        private val communicationUiStateUser: CommunicationUiStateUser,
        private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : UsersViewModel, ViewModel() {

        override fun users() {
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

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiStateUser>>)
            = communicationUiStateUser.observe(owner,observer)

    }
}