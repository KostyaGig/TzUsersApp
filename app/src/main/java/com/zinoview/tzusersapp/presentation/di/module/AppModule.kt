package com.zinoview.tzusersapp.presentation.di.module

import android.content.Context
import com.zinoview.tzusersapp.core.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class,DomainModule::class,UiModule::class])
class AppModule(
    private val context: Context
) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideResourceProvider(context: Context) : ResourceProvider {
        return ResourceProvider.Base(context)
    }
}