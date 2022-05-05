package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import android.content.Context
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
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressEndScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressStartScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.showErrorSnack
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_USER_LOGIN = "ARG_USER_LOGIN"

class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {

    private val controller by lazy { activity as UserDetailsController }

    private val viewModel: UserDetailsViewModel by viewModel()

//    private val viewModel: UserDetailsViewModel by viewModels {
//        UserDetailsViewModelFactory(App.repository)
//    }

    companion object {
        fun newInstance(login: String): UserDetailsFragment {
            return UserDetailsFragment().apply {
                arguments = bundleOf(ARG_USER_LOGIN to login)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        checkIsActivityImplementsController()
    }

    private fun checkIsActivityImplementsController() {
        if (activity !is UserDetailsController) {
            throw IllegalStateException(
                getString(R.string.activity_not_is_user_details_controller))
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
                    userDetailsAvatarImageView.load(state.user.avatar)
                    userDetailsLoginTextView.text = state.user.login
                    userDetailsNameTextView.text = state.user.name
                    userDetailsIdTextView.text = state.user.id
                    userDetailsCreatedAtTextView.text = state.user.registrationDate.substring(0, 10)
                    userDetailsPublicReposTextView.text = state.user.publicRepos.toString()
                }

                is UserDetailsState.UserRepositoriesSuccess -> {
                    userRepositoriesListRecyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter =
                            UserDetailsRecyclerViewAdapter(object : OnRepositoryItemClickListener {
                                override fun onUserSearchItemClickListener(repositoryName: String) {
                                    controller.showRepositoryDetails(
                                        userDetailsLoginTextView.text.toString(),
                                        repositoryName)
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

interface UserDetailsController {
    fun showRepositoryDetails(repositoryOwnerLogin: String, repositoryName: String)
}