package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.repository.DataCallback
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserGitHubRepositoryDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserGitHubRepositoryViewModel(
    private val repository: DataGetGitHubRepositoryUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserGitHubRepositoryDetailsState> = MutableLiveData()

    fun getLiveData(): MutableLiveData<UserGitHubRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress)
        repository.getGitHubRepository(owner,
            name, object : DataCallback<GitHubUserRepositoryDto> {
                override fun onSuccess(result: GitHubUserRepositoryDto) {
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