package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.Abstract

interface MapperDomainUserToUi : Abstract.UserMapper<UiUser> {

    class Base : MapperDomainUserToUi {
        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiUser
            = UiUser.Base(
                id, email, firstName, lastName, avatar
            )
    }
}