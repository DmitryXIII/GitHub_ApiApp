package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchBinding
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding>(FragmentUserSearchBinding::inflate) {
    private val viewModel by viewModels<UserSearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.userSearchInputLayout.setEndIconOnClickListener {
            viewModel.searchUsers(binding.userSearchEditText.text.toString())
        }
    }

    private fun renderData(state: UserSearchState) {
        when (state) {
            UserSearchState.UserSearchProgress -> {}
            is UserSearchState.UserSearchSuccess -> {
                binding.tempTextView.text = state.userList.toString()
            }
            is UserSearchState.UserSearchError -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}