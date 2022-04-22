package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import coil.load
import com.ineedyourcode.githubapiapp.databinding.FragmentUserDetailsBinding
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment

private const val ARG_USER_LOGIN = "ARG_USER_LOGIN"

class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {
    private val viewModel by viewModels<UserDetailsViewModel>()

    companion object {
        fun newInstance(login: String): UserDetailsFragment {
            return UserDetailsFragment().apply { arguments = bundleOf(ARG_USER_LOGIN to login) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        arguments?.getString(ARG_USER_LOGIN)?.let { userLogin ->
            viewModel.getUser(userLogin)
        }
    }

    private fun renderData(state: UserDetailsState) {
        when (state) {
            UserDetailsState.UserDetailsProgress -> {}
            is UserDetailsState.UserDetailsSuccess -> {
                with(binding) {
                    userDetailsAvatarImageView.load(state.user.avatarUrl)
                    userDetailsLoginTextView.text = state.user.login
                    userDetailsNameTextView.text = state.user.name
                    userDetailsIdTextView.text = state.user.id.toString()
                    userDetailsCreatedAtTextView.text = state.user.createdAt.substring(0, 10)
                    userDetailsPublicReposTextView.text = state.user.publicRepos.toString()
                }
            }
            is UserDetailsState.UserDetailsError -> {}
        }
    }
}