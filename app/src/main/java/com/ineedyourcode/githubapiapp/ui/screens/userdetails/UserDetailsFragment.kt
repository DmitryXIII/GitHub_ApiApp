package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ineedyourcode.githubapiapp.App
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.databinding.FragmentUserDetailsBinding
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.recyclerviewadapter.OnRepositoryItemClickListener
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.recyclerviewadapter.UserDetailsRecyclerViewAdapter
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel.UserDetailsViewModel
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel.UserDetailsViewModelFactory
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserRepositoryDetailsFragment
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressEndScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressStartScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.showErrorSnack

private const val ARG_USER_LOGIN = "ARG_USER_LOGIN"

class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {

    private val viewModel: UserDetailsViewModel by viewModels {
        UserDetailsViewModelFactory(App.retrofitRepository)
    }

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
        with(binding) {
            when (state) {
                UserDetailsState.UserDetailsProgress -> {
                    setInProgressStartScreenVisibility(progressBar, userDetailsLayout)
                }

                is UserDetailsState.UserDetailsSuccess -> {
                    userDetailsAvatarImageView.load(state.user.avatarUrl)
                    userDetailsLoginTextView.text = state.user.login
                    userDetailsNameTextView.text = state.user.name
                    userDetailsIdTextView.text = state.user.id.toString()
                    userDetailsCreatedAtTextView.text = state.user.createdAt.substring(0, 10)
                    userDetailsPublicReposTextView.text = state.user.publicRepos.toString()
                }

                is UserDetailsState.UserRepositoriesSuccess -> {
                    userRepositoriesListRecyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter =
                            UserDetailsRecyclerViewAdapter(object : OnRepositoryItemClickListener {
                                override fun onUserSearchItemClickListener(repositoryName: String) {
                                    parentFragmentManager
                                        .beginTransaction()
                                        .add(R.id.main_fragment_container_view,
                                            UserRepositoryDetailsFragment.newInstance(
                                                userDetailsLoginTextView.text.toString(),
                                                repositoryName))
                                        .addToBackStack("")
                                        .commit()
                                }

                            }).apply {
                                setData(state.repositoriesList)
                            }
                        setInProgressEndScreenVisibility(progressBar, userDetailsLayout)
                    }
                }

                is UserDetailsState.UserDetailsError -> {
                    setInProgressEndScreenVisibility(progressBar, userDetailsLayout)
                    showErrorSnack(root, state.error)
                }
            }
        }
    }
}