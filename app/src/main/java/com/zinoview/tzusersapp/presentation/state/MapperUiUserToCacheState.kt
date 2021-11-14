package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.core.Abstract

interface MapperUiUserToCacheState : Abstract.UserMapper<UiStateUser> {

    class Base : MapperUiUserToCacheState {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiStateUser
            = UiStateUser.Cache(
                id, email, firstName, lastName, avatar
            )

    }
}