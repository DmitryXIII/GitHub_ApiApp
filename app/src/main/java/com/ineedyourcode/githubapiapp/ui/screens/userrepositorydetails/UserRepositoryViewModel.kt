package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryViewModel(
    private val retrofitRepository: GitHubApi,
    private val liveData: MutableLiveData<UserRepositoryDetailsState> = MutableLiveData(),
) : ViewModel() {
    fun getLiveData(): MutableLiveData<UserRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsProgress)
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
                                UserRepositoryDetailsState.UserRepositoryDetailsSuccess(
                                    gitHubRepository))
                        }
                    } else {
                        liveData.postValue(
                            UserRepositoryDetailsState.UserRepositoryDetailsError(
                                MessageMapper.StringResource(
                                    MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                    }
                }

                override fun onFailure(call: Call<GitHubUserRepository>, t: Throwable) {
                    liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsError(
                        MessageMapper.DirectString(t.message.toString())))
                }
            })
    }
}