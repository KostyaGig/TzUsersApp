package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.presentation.core.log

interface MapperUiUsersToState : Abstract.UsersMapper<List<UiStateUser>> {

    class Base(
        private val commonStateMapper: MapperUiUserToCommonState,
        private val cacheStateMapper: MapperUiUserToCacheState
    ) : MapperUiUsersToState {

        override fun map(users: List<BaseUser>): List<UiStateUser>
                = users.map { uiUser -> uiUser.map(commonStateMapper) }

        override fun mapCache(users: List<BaseUser>): List<UiStateUser> {
            log("users map cache ${users.size}")
            return users.map { uiUser -> uiUser.map(cacheStateMapper) }
        }

        override fun map(message: String): List<UiStateUser>
                = listOf(UiStateUser.Failure(message))
    }

}