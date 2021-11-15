package com.zinoview.tzusersapp.presentation

import android.widget.EditText
import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser
import java.io.Serializable

interface BundleUser : BaseUser, Serializable {

    fun fullFieldsValues(editFirstNameField: EditText, editLastNameField: EditText)

    fun modify(firstName: String, lastName: String) : BaseUser

    class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : BundleUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)

        override fun fullFieldsValues(editFirstNameField: EditText, editLastNameField: EditText) {
            editFirstNameField.setText(firstName)
            editLastNameField.setText(lastName)
        }

        override fun modify(firstName: String, lastName: String): BaseUser
            = Base(
                id, email, firstName, lastName, avatar
            )
    }
}