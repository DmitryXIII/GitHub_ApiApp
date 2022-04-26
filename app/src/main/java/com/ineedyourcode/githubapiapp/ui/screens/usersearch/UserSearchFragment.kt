package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.githubapiapp.App
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchBinding
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.OnUserSearchItemClickListener
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.UserSearchRecyclerViewAdapter
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel.UserSearchViewModel
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel.UserSearchViewModelFactory
import com.ineedyourcode.githubapiapp.ui.utils.*

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding>(FragmentUserSearchBinding::inflate) {

    private val viewModel: UserSearchViewModel by viewModels {
        UserSearchViewModelFactory(App.retrofitRepository)
    }

    private lateinit var userSearchAdapter: UserSearchRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSearchAdapter = UserSearchRecyclerViewAdapter(object : OnUserSearchItemClickListener {
            override fun onUserSearchItemClickListener(login: String) {
                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.main_fragment_container_view, UserDetailsFragment.newInstance(login))
                    .addToBackStack(getString(R.string.empty_text))
                    .commit()
            }
        })

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        if (savedInstanceState == null) {
            viewModel.getMostPopularUsers()
        }

        binding.userSearchEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchUserOnGitHub()
                }
            }
            false
        }

        binding.userSearchInputLayout.setEndIconOnClickListener {
            searchUserOnGitHub()
        }
    }

    private fun searchUserOnGitHub() {
        binding.root.hideKeyboard()
        viewModel.searchGitHubUser(binding.userSearchEditText.text.toString())
    }

    private fun renderData(state: UserSearchState) {
        with(binding) {
            when (state) {
                UserSearchState.UserSearchProgress -> {
                    setInProgressStartScreenVisibility(progressBar, userSearchLayout)
                }

                is UserSearchState.UserSearchSuccess -> {
                    userSearchAdapter.setData(state.userList)
                    userListRecyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = userSearchAdapter
                    }
                    setInProgressEndScreenVisibility(progressBar, userSearchLayout)
                }

                is UserSearchState.UserSearchError -> {
                    setInProgressEndScreenVisibility(progressBar, userSearchLayout)
                    userSearchAdapter.clearData()
                    showErrorSnack(root, state.error)
                }
            }
        }
    }
}