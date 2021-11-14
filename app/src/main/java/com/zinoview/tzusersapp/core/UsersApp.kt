package com.zinoview.tzusersapp.core

import android.app.Application
import com.squareup.picasso.BuildConfig
import com.zinoview.tzusersapp.presentation.di.component.AppComponent
import com.zinoview.tzusersapp.presentation.di.component.DaggerAppComponent
import com.zinoview.tzusersapp.presentation.di.module.AppModule
import timber.log.Timber

class UsersApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}