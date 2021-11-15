package com.zinoview.tzusersapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.databinding.UsersFragmentBinding
import com.zinoview.tzusersapp.presentation.BundleUser
import com.zinoview.tzusersapp.presentation.ModifyUser
import com.zinoview.tzusersapp.presentation.UsersViewModel
import com.zinoview.tzusersapp.presentation.UsersViewModelFactory
import com.zinoview.tzusersapp.presentation.adapter.ModifyItemClickListener
import com.zinoview.tzusersapp.presentation.adapter.UsersAdapter
import com.zinoview.tzusersapp.presentation.core.BaseFragment
import com.zinoview.tzusersapp.presentation.core.ExitActivity
import com.zinoview.tzusersapp.presentation.core.log
import javax.inject.Inject

class UsersFragment : BaseFragment(R.layout.users_fragment) {

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory

    private val usersViewModel: UsersViewModel by viewModels {
        usersViewModelFactory
    }

    private var _binding: UsersFragmentBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UsersFragmentBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = UsersAdapter.Base(object : ModifyItemClickListener {

            override fun onDeleteItem(email: String) {
                usersViewModel.modifyUser(ModifyUser.Delete(email))
            }

            override fun onEditItem(bundleUser: BundleUser) {

                val fragment = UserEditFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(USER_KEY,bundleUser)
                    }
                }

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit()

            }

        })
        binding.usersRecView.adapter = adapter

        usersViewModel.observe(this) { uiStateUser ->
            uiStateUser.first().handleTitleToolbar(checkNotNull(toolbar))
            log("first uiStateUser ${uiStateUser.first().javaClass}")
            adapter.show(uiStateUser)
        }

        usersViewModel.users()

    }

    override fun navigateToBack() {
        (requireActivity() as ExitActivity).exit()
    }
}