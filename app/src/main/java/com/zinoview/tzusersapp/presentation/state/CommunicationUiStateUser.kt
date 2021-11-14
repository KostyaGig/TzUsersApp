package com.zinoview.tzusersapp.presentation.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zinoview.tzusersapp.presentation.core.Observe

interface CommunicationUiStateUser : Observe<List<UiStateUser>> {

    fun postValue(value : List<UiStateUser>)

    class Base : CommunicationUiStateUser {

        private val liveData = MutableLiveData<List<UiStateUser>>()

        override fun postValue(value: List<UiStateUser>) {
            liveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiStateUser>>)
            = liveData.observe(owner,observer)

    }
}