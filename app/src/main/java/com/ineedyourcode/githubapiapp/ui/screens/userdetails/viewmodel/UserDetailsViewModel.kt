package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.DataCallback
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserDetailsViewModel(
    private val repository: DataGetGitHubUserUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData()

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        repository.getGitHubUser(login, object : DataCallback<GitHubUserProfile> {
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
            object : DataCallback<List<GitHubUserRepository>> {
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