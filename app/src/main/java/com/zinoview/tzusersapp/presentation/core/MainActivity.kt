package com.zinoview.tzusersapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.databinding.ActivityMainBinding
import com.zinoview.tzusersapp.presentation.di.component.AppComponent
import timber.log.Timber
import java.lang.IllegalArgumentException

//todo remove later
fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
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