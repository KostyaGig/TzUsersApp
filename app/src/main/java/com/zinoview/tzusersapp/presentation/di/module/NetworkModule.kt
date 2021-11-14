package com.zinoview.tzusersapp.presentation.di.module

import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import com.zinoview.tzusersapp.data.cloud.CloudUser
import com.zinoview.tzusersapp.data.cloud.CloudUserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    }

    @Provides
    fun provideGson() = GsonConverterFactory.create()

    private companion object {
        private const val BASE_URL = "https://reqres.in/api/"
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient,gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideCloudUserService(retrofit: Retrofit) : CloudUserService {
        return retrofit.create(CloudUserService::class.java)
    }

    @Provides
    fun provideBaseCloudDataSource(service: CloudUserService) : CloudDataSource<CloudUser> {
        return CloudDataSource.Base(service)
    }
}