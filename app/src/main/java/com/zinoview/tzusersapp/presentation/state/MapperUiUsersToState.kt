package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

interface MapperUiUsersToState : Abstract.UsersMapper<List<UiStateUser>> {

    class Base(
        private val mapper: MapperUiUserToState
    ) : MapperUiUsersToState {

        override fun map(users: List<BaseUser>): List<UiStateUser>
                = users.map { uiUser -> uiUser.map(mapper) }

        override fun map(message: String): List<UiStateUser>
                = listOf(UiStateUser.Failure(message))
    }

}