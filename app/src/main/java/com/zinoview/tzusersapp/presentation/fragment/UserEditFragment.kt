package com.zinoview.tzusersapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zinoview.tzusersapp.R
import com.zinoview.tzusersapp.core.ResourceProvider
import com.zinoview.tzusersapp.databinding.UserEditFragmentBinding
import com.zinoview.tzusersapp.presentation.BundleUser
import com.zinoview.tzusersapp.presentation.ModifyUser
import com.zinoview.tzusersapp.presentation.UsersViewModel
import com.zinoview.tzusersapp.presentation.UsersViewModelFactory
import com.zinoview.tzusersapp.presentation.core.BaseFragment
import com.zinoview.tzusersapp.presentation.validation.UsersDataValidator
import javax.inject.Inject

class UserEditFragment : BaseFragment(R.layout.user_edit_fragment) {

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory

    private val usersViewModel: UsersViewModel by viewModels {
        usersViewModelFactory
    }

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var _binding: UserEditFragmentBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    private val usersDataValidator = UsersDataValidator.Base()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = UserEditFragmentBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let { bundle ->
            val editFirstNameField = binding.editFirstNameField
            val editLastNameField = binding.editLastNameField

            val user = bundle.getSerializable(USER_KEY) as BundleUser
            user.fullFieldsValues(editFirstNameField,editLastNameField)

            binding.updateUserBtn.setOnClickListener {

                if (usersDataValidator.isValid(editFirstNameField,editLastNameField)) {
                    val modifiedUser = user.modify(editFirstNameField.text.toString(),editLastNameField.text.toString())
                    usersViewModel.modifyUser(ModifyUser.Edit(
                        modifiedUser
                    ))
                    navigateToBack()
                } else {
                    Toast.makeText(requireContext(), R.string.fields_not_will_be_empty_text, Toast.LENGTH_SHORT).show()
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToBack()
                }
            })

        }
    }

    private fun navigateToBack() {
        findNavController().navigate(R.id.action_userEditFragment_to_usersFragment)
    }


}