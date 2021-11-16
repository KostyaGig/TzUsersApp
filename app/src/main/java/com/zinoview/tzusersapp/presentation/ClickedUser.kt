package com.zinoview.tzusersapp.presentation

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzusersapp.core.Abstract
import com.zinoview.tzusersapp.core.BaseUser
import java.io.Serializable

interface ClickedUser : BaseUser,Serializable {

    fun fullUi(avatarImage: ImageView, textViews: List<TextView>)

    class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : ClickedUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)

        override fun fullUi(avatarImage: ImageView, textViews: List<TextView>) {
            Picasso.get().load(avatar).into(avatarImage)
            textViews[0].text = firstName
            textViews[1].text = lastName
            textViews[2].text = email
            textViews[3].text = ID + id
        }

        private companion object {
            private const val ID = "id: "
        }
    }
}