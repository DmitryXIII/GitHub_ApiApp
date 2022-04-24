package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsViewModel(
    private val retrofitRepository : GitHubApi,
    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData(),
) : ViewModel() {

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        retrofitRepository.getUser(login, object : Callback<GitHubUserProfile> {
            override fun onResponse(
                call: Call<GitHubUserProfile>,
                response: Response<GitHubUserProfile>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { user ->
                        liveData.postValue(UserDetailsState.UserDetailsSuccess(user))
                        getUserRepositories(login)
                    }
                } else {
                    liveData.postValue(UserDetailsState.UserDetailsError(
                        MessageMapper.StringResource(
                            MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            }

            override fun onFailure(call: Call<GitHubUserProfile>, t: Throwable) {
                liveData.postValue(UserDetailsState.UserDetailsError(
                    MessageMapper.DirectString(t.message.toString())))
            }
        })
    }

    private fun getUserRepositories(login: String) {
        retrofitRepository.getUserGitHubRepositories(login,
            object : Callback<List<GitHubUserRepository>> {
                override fun onResponse(
                    call: Call<List<GitHubUserRepository>>,
                    response: Response<List<GitHubUserRepository>>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { repositoriesList ->
                            liveData.postValue(UserDetailsState.UserRepositoriesSuccess(
                                repositoriesList))
                        }
                    } else {
                        liveData.postValue(UserDetailsState.UserDetailsError(
                            MessageMapper.StringResource(
                                MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                    }
                }

                override fun onFailure(call: Call<List<GitHubUserRepository>>, t: Throwable) {
                    liveData.postValue(UserDetailsState.UserDetailsError(
                        MessageMapper.DirectString(t.message.toString())))
                }
            })
    }
}