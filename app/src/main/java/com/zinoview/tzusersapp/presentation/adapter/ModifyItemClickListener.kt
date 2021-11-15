package com.zinoview.tzusersapp.presentation.adapter

import com.zinoview.tzusersapp.presentation.BundleUser

interface ModifyItemClickListener {

    fun onDeleteItem(email: String)

    fun onEditItem(bundleUser: BundleUser)
}