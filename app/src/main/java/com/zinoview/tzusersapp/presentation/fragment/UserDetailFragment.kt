package com.zinoview.tzusersapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.databinding.UserDetailFragmentBinding
import com.zinoview.tzusersapp.presentation.ClickedUser
import com.zinoview.tzusersapp.presentation.core.BaseFragment

class UserDetailFragment : BaseFragment(R.layout.user_detail_fragment) {

    private var _binding: UserDetailFragmentBinding? = null

    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserDetailFragmentBinding.inflate(layoutInflater)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let { bundle ->
            val clickedUser = bundle.getSerializable(USER_KEY) as ClickedUser

            val avatarImage = binding.avatarImage
            val firstNameText = binding.firstNameTv
            val lastNameText = binding.lastNameTv
            val emailText = binding.emailTv
            val idText = binding.idTv

            clickedUser.fullUi(avatarImage, listOf(firstNameText,lastNameText,emailText,idText))
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_userDetailFragment_to_usersFragment)
            }
        })
    }

    override fun navigateToBack() {
        findNavController().navigate(R.id.action_userDetailFragment_to_usersFragment)
    }

}