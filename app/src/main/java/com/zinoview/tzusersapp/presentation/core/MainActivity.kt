package com.zinoview.tzusersapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.core.UsersApp
import com.zinoview.tzusersapp.databinding.ActivityMainBinding
import com.zinoview.tzusersapp.presentation.di.component.AppComponent
import com.zinoview.tzusersapp.presentation.fragment.UsersFragment
import timber.log.Timber
import java.lang.IllegalArgumentException

//todo remove later
fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}


//todo use navigation component for nav
interface ExitActivity {

    fun exit()
}

class MainActivity : AppCompatActivity(),ExitActivity {

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

    override fun onBackPressed() {
        val baseFragment = supportFragmentManager.fragments[0] as BaseFragment
        baseFragment.navigateToBack()
    }

    override fun exit()
        = finish()
}