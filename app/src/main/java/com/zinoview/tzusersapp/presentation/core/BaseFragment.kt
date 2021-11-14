package com.zinoview.tzusersapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.presentation.fragment.UsersFragment

abstract class BaseFragment(@LayoutRes id: Int) : Fragment(id) {

    protected fun inject(fragment: BaseFragment) {
        if (fragment is UsersFragment) {
            (requireActivity().application as UsersApp).component.inject(fragment)
        }
    }

    protected val toolbar by lazy {
        (requireActivity() as MainActivity).supportActionBar
    }
}