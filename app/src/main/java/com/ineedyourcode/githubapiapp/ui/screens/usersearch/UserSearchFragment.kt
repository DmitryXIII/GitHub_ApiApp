package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ineedyourcode.githubapiapp.App
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchBinding
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.OnUserSearchItemClickListener
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.UserSearchRecyclerViewAdapter
import com.ineedyourcode.githubapiapp.ui.utils.*

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding>(FragmentUserSearchBinding::inflate) {

    private val viewModel: UserSearchViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserSearchViewModel(App.retrofitRepository) as T
            }
        }
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

        viewModel.getMostPopularUsers()

        binding.userSearchInputLayout.setEndIconOnClickListener {
            viewModel.searchUsers(binding.userSearchEditText.text.toString())
        }
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