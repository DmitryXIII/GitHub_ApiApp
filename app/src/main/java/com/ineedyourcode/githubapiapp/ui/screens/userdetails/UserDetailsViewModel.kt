package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.retrofit.IRetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsViewModel(
    private val retrofitRepository: IRetrofitGitHubRepository,
    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData(),
) : ViewModel() {

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        retrofitRepository.getUser(login, object : Callback<GitHubUserProfileDto> {
            override fun onResponse(
                call: Call<GitHubUserProfileDto>,
                response: Response<GitHubUserProfileDto>,
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

            override fun onFailure(call: Call<GitHubUserProfileDto>, t: Throwable) {
                liveData.postValue(UserDetailsState.UserDetailsError(
                    MessageMapper.DirectString(t.message.toString())))
            }
        })
    }

    private fun getUserRepositories(login: String) {
        retrofitRepository.getUserRepositories(login,
            object : Callback<List<GitHubUserRepositoryDto>> {
                override fun onResponse(
                    call: Call<List<GitHubUserRepositoryDto>>,
                    response: Response<List<GitHubUserRepositoryDto>>,
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

                override fun onFailure(call: Call<List<GitHubUserRepositoryDto>>, t: Throwable) {
                    liveData.postValue(UserDetailsState.UserDetailsError(
                        MessageMapper.DirectString(t.message.toString())))
                }
            })
    }
}