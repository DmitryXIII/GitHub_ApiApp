package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.repository.DataCallback
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserDetailsViewModel(
    private val repository: DataGetGitHubUserUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData()

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        repository.getGitHubUser(login, object : DataCallback<GitHubUserProfileDto> {
            override fun onSuccess(result: GitHubUserProfileDto) {
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
            object : DataCallback<List<GitHubUserRepositoryDto>> {
                override fun onSuccess(result: List<GitHubUserRepositoryDto>) {
                    liveData.postValue(UserDetailsState.UserRepositoriesSuccess(result))
                }

                override fun onFail(error: Throwable) {
                    liveData.postValue(UserDetailsState.UserDetailsError(
                        MessageMapper.DirectString(error.message.toString())))
                }
            })
    }
}