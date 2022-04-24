package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserGitHubRepositoryDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserGitHubRepositoryViewModel(
    private val retrofitRepository: GetGitHubRepositoryUsecase,
    private val liveData: MutableLiveData<UserGitHubRepositoryDetailsState> = MutableLiveData(),
) : ViewModel() {
    fun getLiveData(): MutableLiveData<UserGitHubRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress)
        retrofitRepository.getGitHubRepository(owner,
            name,
            object : Callback<GitHubUserRepository> {
                override fun onResponse(
                    call: Call<GitHubUserRepository>,
                    response: Response<GitHubUserRepository>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { gitHubRepository ->
                            liveData.postValue(
                                UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsSuccess(
                                    gitHubRepository))
                        }
                    } else {
                        liveData.postValue(
                            UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError(
                                MessageMapper.StringResource(
                                    MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                    }
                }

                override fun onFailure(call: Call<GitHubUserRepository>, t: Throwable) {
                    liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError(
                        MessageMapper.DirectString(t.message.toString())))
                }
            })
    }
}