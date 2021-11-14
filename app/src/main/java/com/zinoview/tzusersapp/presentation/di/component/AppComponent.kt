package com.zinoview.tzusersapp.presentation.di.component

import com.zinoview.tzusersapp.presentation.core.MainActivity
import com.zinoview.tzusersapp.presentation.di.module.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}