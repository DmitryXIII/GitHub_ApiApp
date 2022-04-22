package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.data.retrofit.IRetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.data.retrofit.RetrofitGitHubRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsViewModel(
    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData(),
    private val retrofitRepository: IRetrofitGitHubRepository = RetrofitGitHubRepository(),
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
                    liveData.postValue(UserDetailsState.UserDetailsError("Нет результатов по данному запросу"))
                }
            }

            override fun onFailure(call: Call<GitHubUserProfileDto>, t: Throwable) {
                liveData.postValue(UserDetailsState.UserDetailsError(t.message.toString()))
            }
        })
    }

    private fun getUserRepositories(login: String) {
        retrofitRepository.getUserRepositories(login, object : Callback<List<GitHubUserRepositoryDto>> {
            override fun onResponse(
                call: Call<List<GitHubUserRepositoryDto>>,
                response: Response<List<GitHubUserRepositoryDto>>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { repositoriesList ->
                        liveData.postValue(UserDetailsState.UserRepositoriesSuccess(repositoriesList))
                    }
                } else {
                    liveData.postValue(UserDetailsState.UserDetailsError("Нет результатов по данному запросу"))
                }
            }

            override fun onFailure(call: Call<List<GitHubUserRepositoryDto>>, t: Throwable) {
                liveData.postValue(UserDetailsState.UserDetailsError(t.message.toString()))
            }
        })
    }
}