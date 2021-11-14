package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.core.ResourceProvider
import retrofit2.HttpException
import java.lang.IllegalArgumentException
import java.net.UnknownHostException

interface ExceptionMapper<T> {

    fun map(e: Exception) : T

    class Base(
        private val resourceProvider: ResourceProvider
    ) : ExceptionMapper<String> {

        override fun map(e: Exception): String {
            return when(e) {
                is UnknownHostException -> resourceProvider.string(R.string.no_connection_text)
                is HttpException -> resourceProvider.string(R.string.some_went_wrong_text)
                else -> throw IllegalArgumentException("ExceptionMapper.Base not handle arg ${e.javaClass}")
            }
        }
    }

    class Test : ExceptionMapper<String> {

        override fun map(e: Exception): String {
            return when(e) {
                is UnknownHostException -> "No connection"
                is HttpException -> "Some went wrong"
                else -> throw IllegalArgumentException("ExceptionMapper.Base not handle arg ${e.javaClass}")
            }
        }
    }
}