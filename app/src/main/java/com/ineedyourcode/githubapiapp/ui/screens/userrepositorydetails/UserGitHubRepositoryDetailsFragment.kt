package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ineedyourcode.githubapiapp.app
import com.ineedyourcode.githubapiapp.databinding.FragmentUserRepositoryDetailsBinding
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel.UserGitHubRepositoryViewModel
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel.UserGitHubRepositoryViewModelFactory
import com.ineedyourcode.githubapiapp.ui.utils.BaseFragment
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressEndScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.setInProgressStartScreenVisibility
import com.ineedyourcode.githubapiapp.ui.utils.showErrorSnack
import javax.inject.Inject

private const val ARG_REPOSITORY_OWNER = "ARG_REPOSITORY_OWNER"
private const val ARG_REPOSITORY_NAME = "ARG_REPOSITORY_NAME"

class UserRepositoryDetailsFragment :
    BaseFragment<FragmentUserRepositoryDetailsBinding>(
        FragmentUserRepositoryDetailsBinding::inflate
    ) {

    @Inject
    lateinit var repository: GetProjectRepositoryUsecase

    private val viewModel: UserGitHubRepositoryViewModel by viewModels {
        UserGitHubRepositoryViewModelFactory(repository)
    }

    companion object {
        fun newInstance(
            repositoryOwner: String,
            repositoryName: String,
        ): UserRepositoryDetailsFragment {
            return UserRepositoryDetailsFragment().apply {
                arguments = bundleOf(ARG_REPOSITORY_OWNER to repositoryOwner,
                    ARG_REPOSITORY_NAME to repositoryName)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.app?.appDependenciesComponent?.inject3(this)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        arguments?.getString(ARG_REPOSITORY_OWNER)?.let { owner ->
            arguments?.getString(ARG_REPOSITORY_NAME)?.let { name ->
                viewModel.getGitHubRepository(owner, name)
            }
        }
    }

    private fun renderData(state: UserGitHubRepositoryDetailsState) {
        with(binding) {
            when (state) {
                UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress -> {
                    setInProgressStartScreenVisibility(progressBar, userRepositoryDetailsLayout)
                }

                is UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsSuccess -> {
                    repositoryDetailsNameTextView.text = state.repository.name
                    repositoryDetailsIdTextView.text = state.repository.id
                    repositoryDetailsCreatedAtTextView.text =
                        state.repository.createDate.substring(0, 10)
                    repositoryDetailsHtmlUrlTextView.text = state.repository.url
                    repositoryDetailsLanguageTextView.text = state.repository.language
                    repositoryDetailsDescriptionTextView.text = state.repository.description
                    setInProgressEndScreenVisibility(progressBar, userRepositoryDetailsLayout)
                }

                is UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError -> {
                    setInProgressEndScreenVisibility(progressBar, userRepositoryDetailsLayout)
                    showErrorSnack(root, state.error)
                }
            }
        }
    }
}