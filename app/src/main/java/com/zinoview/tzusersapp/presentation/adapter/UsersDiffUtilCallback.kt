package com.zinoview.tzusersapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zinoview.tzusersapp.presentation.state.UiStateUser

class UsersDiffUtilCallback(
    private val oldList: List<UiStateUser>,
    private val newList: List<UiStateUser>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int
        = oldList.size

    override fun getNewListSize(): Int
        = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.sameId(newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.same(newItem)
    }
}