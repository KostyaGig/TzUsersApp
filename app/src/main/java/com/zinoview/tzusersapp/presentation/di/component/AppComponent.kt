package com.zinoview.tzusersapp.presentation.di.component

import com.zinoview.tzusersapp.presentation.core.MainActivity
import com.zinoview.tzusersapp.presentation.di.module.AppModule
import com.zinoview.tzusersapp.presentation.fragment.UserEditFragment
import com.zinoview.tzusersapp.presentation.fragment.UsersFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(usersFragment: UsersFragment)
    fun inject(usersEditFragment: UserEditFragment)
}