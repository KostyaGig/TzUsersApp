package com.zinoview.tzusersapp.presentation.state

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import com.zinoview.tzusersapp.presentation.BundleUser
import com.zinoview.tzusersapp.presentation.adapter.ModifyItemClickListener
import com.zinoview.tzusersapp.presentation.adapter.SameUiStateUser
import com.zinoview.tzusersapp.presentation.core.log

sealed class UiStateUser : SameUiStateUser, ModifyItem {

    open fun bind(avatarImage: ImageView,firstNameText: TextView,lastNameText: TextView,emailText: TextView) = Unit

    open fun bind(errorText: TextView) = Unit

    open fun showModifyIcons(edit: ImageView,delete: ImageView) = Unit

    abstract fun handleTitleToolbar(actionBar: ActionBar)

    override fun same(item: UiStateUser): Boolean
        = false

    override fun same(email: String, firstName: String): Boolean
        = false

    override fun sameId(item: UiStateUser): Boolean
        = false

    override fun sameId(id: Int): Boolean
        = false

    override fun onEditItem(modifyItemClickListener: ModifyItemClickListener) = Unit

    override fun onDeleteItem(modifyItemClickListener: ModifyItemClickListener) = Unit

    object Progress : UiStateUser() {

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        private const val TOOLBAR_TITLE = "Progress..."
    }

    abstract class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : UiStateUser() {

        override fun sameId(item: UiStateUser): Boolean
                = item.sameId(id)

        override fun sameId(id: Int): Boolean
                = this.id == id

        override fun same(item: UiStateUser): Boolean
                = item.same(email, firstName)

        override fun same(email: String, firstName: String): Boolean
                = this.email == email && this.firstName == firstName

        override fun bind(avatarImage: ImageView,firstNameText: TextView,lastNameText: TextView,emailText: TextView) {
            log("base bind")
            Picasso.get().load(avatar).into(avatarImage)
            firstNameText.text = firstName
            lastNameText.text = lastName
            emailText.text = email
        }
    }

    class Common(
        id: Int,
        email: String,
        firstName: String,
        lastName: String,
        avatar: String
    ) : Base(id, email, firstName, lastName, avatar) {

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        override fun showModifyIcons(edit: ImageView, delete: ImageView) {
            edit.visibility = View.GONE
            delete.visibility = View.GONE
        }

        private companion object {
            private const val TOOLBAR_TITLE = "Users(Remote)"
        }
    }

    class Cache(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : Base(id, email, firstName, lastName, avatar) {

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TOOLBAR_TITLE
        }

        override fun showModifyIcons(edit: ImageView, delete: ImageView) {
            edit.visibility = View.VISIBLE
            delete.visibility = View.VISIBLE
        }

        override fun onEditItem(modifyItemClickListener: ModifyItemClickListener)
            = modifyItemClickListener.onEditItem(
                BundleUser.Base(
                    id, email, firstName, lastName, avatar
                )
            )

        override fun onDeleteItem(modifyItemClickListener: ModifyItemClickListener)
            = modifyItemClickListener.onDeleteItem(email)

        private companion object {
            private const val TOOLBAR_TITLE = "Users(Cache)"
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