package com.zinoview.tzusersapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

sealed class UiStateUser {

    open fun bind(avatarImage: ImageView,firstNameText: TextView,lastNameText: TextView,emailText: TextView) = Unit

    open fun bind(errorText: TextView) = Unit

    abstract fun handleTitleToolbar(actionBar: ActionBar)

    object Progress : UiStateUser() {

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        private const val TOOLBAR_TITLE = "Progress..."
    }

    class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : UiStateUser() {

        override fun bind(avatarImage: ImageView,firstNameText: TextView,lastNameText: TextView,emailText: TextView) {
            Picasso.get().load(avatar).into(avatarImage)
            firstNameText.text = firstName
            lastNameText.text = lastName
            emailText.text = email
        }

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        private companion object {
            private const val TOOLBAR_TITLE = "Users"
        }
    }

    class Failure(
        private val message: String
    ) : UiStateUser() {

        override fun bind(errorText: TextView) {
            errorText.text = message
        }

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        private companion object {
            private const val TOOLBAR_TITLE = "Error"
        }
    }
}