package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.core.Abstract

interface MapperUiUserToState : Abstract.UserMapper<UiStateUser> {

    class Base : MapperUiUserToState {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiStateUser
            = UiStateUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}