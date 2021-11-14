package com.zinoview.tzusersapp.presentation

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser

interface MapperDomainUsersToUi : Abstract.UsersMapper<UiUsers> {

    class Base(
        private val mapperDomainUserToUi: MapperDomainUserToUi
    ) : MapperDomainUsersToUi {

        override fun map(users: List<BaseUser>): UiUsers
            = UiUsers.Success(users.map { domainUser ->
                domainUser.map(mapperDomainUserToUi)
            })

        override fun map(message: String): UiUsers
            = UiUsers.Failure(message)

    }
}