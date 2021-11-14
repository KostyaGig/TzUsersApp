package com.zinoview.tzusersapp.presentation.adapter

import com.zinoview.tzusersapp.presentation.state.UiStateUser

interface SameUiStateUser {

    fun same(item: UiStateUser) : Boolean

    fun sameId(item: UiStateUser) : Boolean

    fun same(email: String,firstName: String) : Boolean

    fun sameId(id: Int) : Boolean
}