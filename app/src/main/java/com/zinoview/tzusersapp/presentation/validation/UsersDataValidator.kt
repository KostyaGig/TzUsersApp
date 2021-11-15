package com.zinoview.tzusersapp.presentation.validation

import android.widget.EditText

interface UsersDataValidator {

    fun isValid(userNameFiled: EditText,userLastNameField: EditText) : Boolean

    class Base : UsersDataValidator {

        override fun isValid(userNameFiled: EditText, userLastNameField: EditText) : Boolean {
            return userNameFiled.textIsNotEmpty() && userLastNameField.textIsNotEmpty()
        }

        private fun EditText.textIsNotEmpty()
            = this.text.trim().isNotEmpty()
    }
}