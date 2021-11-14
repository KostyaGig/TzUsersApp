package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.core.Abstract

interface MapperUiUserToCommonState : Abstract.UserMapper<UiStateUser> {

    class Base : MapperUiUserToCommonState {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiStateUser
            = UiStateUser.Common(
                id, email, firstName, lastName, avatar
            )

    }
}