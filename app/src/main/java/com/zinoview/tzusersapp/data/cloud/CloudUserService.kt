package com.zinoview.tzusersapp.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Base url - https://reqres.in/api/
 * */

interface CloudUserService {

    @GET("users")
    suspend fun data(@Query("page") page: Int = PAGE) : CloudData.Base

    private companion object {
        private const val PAGE = 2
    }
}