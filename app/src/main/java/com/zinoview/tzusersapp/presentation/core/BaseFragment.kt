package com.zinoview.tzusersapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.presentation.fragment.UserEditFragment
import com.zinoview.tzusersapp.presentation.fragment.UsersFragment

abstract class BaseFragment(@LayoutRes id: Int) : Fragment(id) {

    protected fun inject(fragment: BaseFragment) {
        val applicationComponent = (requireActivity().application as UsersApp).component
        when (fragment) {
            is UsersFragment -> applicationComponent.inject(fragment)
            is UserEditFragment -> applicationComponent.inject(fragment)
        }
    }

    protected val toolbar by lazy {
        (requireActivity() as MainActivity).supportActionBar
    }

    protected companion object {
        const val USER_KEY = "user_key"
    }

    abstract fun navigateToBack()
}