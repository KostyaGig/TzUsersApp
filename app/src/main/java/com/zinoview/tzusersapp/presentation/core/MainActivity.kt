package com.zinoview.tzusersapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.data.cloud.CloudUserService
import com.zinoview.tzusersapp.databinding.ActivityMainBinding
import com.zinoview.tzusersapp.presentation.UsersViewModel
import com.zinoview.tzusersapp.presentation.UsersViewModelFactory
import com.zinoview.tzusersapp.presentation.di.component.AppComponent
import com.zinoview.tzusersapp.presentation.fragment.UsersFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalArgumentException
import javax.inject.Inject

//todo remove later
fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        component().inject(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,UsersFragment())
            .commit()
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