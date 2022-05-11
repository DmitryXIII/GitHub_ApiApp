package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchBinding
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.OnUserSearchItemClickListener
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter.UserSearchRecyclerViewAdapter
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel.UserSearchViewModel
import com.ineedyourcode.githubapiapp.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding>(FragmentUserSearchBinding::inflate) {

    private val controller by lazy { activity as UserSearchController }

    private val viewModel: UserSearchViewModel by viewModel()

    private lateinit var userSearchAdapter: UserSearchRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        checkIsActivityImplementsController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSearchAdapter = UserSearchRecyclerViewAdapter(object : OnUserSearchItemClickListener {
            override fun onUserSearchItemClickListener(login: String) {
                controller.showUserDetails(login)
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

    private fun checkIsActivityImplementsController() {
        if (activity !is UserSearchController) {
            throw IllegalStateException(
                getString(R.string.activity_not_is_user_search_controller))
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

interface UserSearchController {
    fun showUserDetails(login: String)
}