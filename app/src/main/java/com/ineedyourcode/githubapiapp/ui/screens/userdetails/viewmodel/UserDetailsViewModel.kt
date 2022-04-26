package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserDetailsViewModel(
    private val repository: GetGitHubUserUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData()

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        repository.getGitHubUser(login, object : GitHubApi.GitHubCallback<GitHubUserProfile> {
            override fun onSuccess(result: GitHubUserProfile) {
                liveData.value = UserDetailsState.UserDetailsSuccess(result)
                getUserGitHubRepositories(login)
            }

            override fun onFail(error: Throwable) {
                liveData.value = UserDetailsState.UserDetailsError(
                    MessageMapper.DirectString(error.message.toString()))
            }
        })
    }

    private fun getUserGitHubRepositories(login: String) {
        repository.getGitHubUserRepositoriesList(login,
            object : GitHubApi.GitHubCallback<List<GitHubUserRepository>> {
                override fun onSuccess(result: List<GitHubUserRepository>) {
                    liveData.postValue(UserDetailsState.UserRepositoriesSuccess(result))
                }

                override fun onFail(error: Throwable) {
                    liveData.postValue(UserDetailsState.UserDetailsError(
                        MessageMapper.DirectString(error.message.toString())))
                }
            })
    }
}