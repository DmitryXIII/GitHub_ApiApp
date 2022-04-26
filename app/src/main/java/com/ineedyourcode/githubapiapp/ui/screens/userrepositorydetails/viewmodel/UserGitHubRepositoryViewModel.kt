package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserGitHubRepositoryDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserGitHubRepositoryViewModel(
    private val repository: GetGitHubRepositoryUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserGitHubRepositoryDetailsState> = MutableLiveData()

    fun getLiveData(): MutableLiveData<UserGitHubRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress)
        repository.getGitHubRepository(owner,
            name, object : GitHubApi.GitHubCallback<GitHubUserRepository> {
                override fun onSuccess(result: GitHubUserRepository) {
                    liveData.postValue(
                        UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsSuccess(result))
                }

                override fun onFail(error: Throwable) {
                    liveData.postValue(
                        UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError(
                            MessageMapper.DirectString(error.message.toString())))
                }
            })
    }
}