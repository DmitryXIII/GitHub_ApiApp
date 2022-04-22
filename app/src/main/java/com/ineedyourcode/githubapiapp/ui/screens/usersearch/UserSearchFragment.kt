package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchBinding
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.OnUserSearchItemClickListener
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.UserSearchRecyclerViewAdapter
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding>(FragmentUserSearchBinding::inflate) {
    private val viewModel by viewModels<UserSearchViewModel>()

    private lateinit var userSearchAdapter: UserSearchRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSearchAdapter = UserSearchRecyclerViewAdapter(object : OnUserSearchItemClickListener {
            override fun onUserSearchItemClickListener(login: String) {
                Toast.makeText(requireContext(), login, Toast.LENGTH_SHORT).show()
            }
        })

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
                userSearchAdapter.setData(state.userList)
                binding.userListRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = userSearchAdapter
                }
            }

            is UserSearchState.UserSearchError -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}