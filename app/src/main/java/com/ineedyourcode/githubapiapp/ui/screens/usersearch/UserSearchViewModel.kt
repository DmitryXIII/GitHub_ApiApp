package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.data.retrofit.IRetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.data.retrofit.RetrofitGitHubRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSearchViewModel(
    private val liveData: MutableLiveData<UserSearchState> = MutableLiveData(),
    private val retrofitRepository: IRetrofitGitHubRepository = RetrofitGitHubRepository(),
) : ViewModel() {

    fun getLiveData(): LiveData<UserSearchState> = liveData

    fun searchUsers(login: String) {
        liveData.postValue(UserSearchState.UserSearchProgress)
        retrofitRepository.searchUsers(login, object : Callback<GitHubUserSearchResultDto> {
            override fun onResponse(
                call: Call<GitHubUserSearchResultDto>,
                response: Response<GitHubUserSearchResultDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        liveData.postValue(UserSearchState.UserSearchSuccess(result.items))
                    }
                } else {
                    liveData.postValue(UserSearchState.UserSearchError("Нет результатов по данному запросу"))
                }
            }

            override fun onFailure(call: Call<GitHubUserSearchResultDto>, t: Throwable) {
                liveData.postValue(UserSearchState.UserSearchError(t.message.toString()))
            }
        })
    }
}