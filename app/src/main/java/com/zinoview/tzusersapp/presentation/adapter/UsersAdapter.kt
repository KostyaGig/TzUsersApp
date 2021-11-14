package com.zinoview.tzusersapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzusersapp.databinding.FailureItemBinding
import com.zinoview.tzusersapp.databinding.ProgressItemBinding
import com.zinoview.tzusersapp.databinding.UserItemBinding
import com.zinoview.tzusersapp.presentation.state.UiStateUser

interface UsersAdapter {

    fun update(items: List<UiStateUser>)

    class Base : UsersAdapter, RecyclerView.Adapter<Base.ViewHolder>() {

        private val users = ArrayList<UiStateUser>()

        override fun update(items: List<UiStateUser>) {
            users.clear()
            users.addAll(items)
            notifyDataSetChanged()
        }

        private companion object {

            private const val PROGRESS = 1
            private const val BASE = 2
            private const val FAILURE = 3
        }

        override fun getItemViewType(position: Int): Int {
            return when(users[position]) {
                is UiStateUser.Progress -> PROGRESS
                is UiStateUser.Base -> BASE
                else -> FAILURE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                PROGRESS -> ViewHolder.Progress(
                    ProgressItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
                BASE -> ViewHolder.Base(
                    UserItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
                else -> ViewHolder.Failure(
                    FailureItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = users[position]
            holder.bind(user)
        }

        override fun getItemCount(): Int
            = users.size

        abstract class ViewHolder(
            view: View
        ) : RecyclerView.ViewHolder(view) {

            open fun bind(user: UiStateUser) {}

            class Progress(view: ProgressItemBinding) : ViewHolder(view.root)

            class Base(private val view: UserItemBinding) : ViewHolder(view.root) {

                override fun bind(user: UiStateUser)
                    = user.bind(view.avatarImage,view.firstNameTv,view.lastNameTv,view.emailTv)
            }
            class Failure(private val view: FailureItemBinding) : ViewHolder(view.root) {

                override fun bind(user: UiStateUser)
                    = user.bind(view.errorTv)
            }
        }
    }
}