package com.zinoview.tzusersapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.data.cloud.CloudUserService
import com.zinoview.tzusersapp.presentation.di.component.AppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalArgumentException
import javax.inject.Inject

//todo remove later
fun Any?.log(message: String) {
    Log.d("zinoviewk",message)
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cloudUserService: CloudUserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component().inject(this)
    }

    private fun Activity.component() : AppComponent {
        val application = application
        return if(application is UsersApp) {
            application.component
        } else {
            throw IllegalArgumentException("Application $application not UsersApp")
        }
    }
}