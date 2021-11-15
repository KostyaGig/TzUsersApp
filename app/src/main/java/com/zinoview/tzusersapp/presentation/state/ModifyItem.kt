package com.zinoview.tzusersapp.presentation.state

import com.zinoview.tzusersapp.presentation.adapter.ModifyItemClickListener

interface ModifyItem {

    fun onEditItem(modifyItemClickListener: ModifyItemClickListener)

    fun onDeleteItem(modifyItemClickListener: ModifyItemClickListener)
}