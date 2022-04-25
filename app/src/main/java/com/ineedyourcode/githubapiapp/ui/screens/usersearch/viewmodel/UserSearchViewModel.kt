package com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.usecase.SearchGitHubUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSearchViewModel(
    private val retrofitRepository: SearchGitHubUserUsecase,
    private val liveData: MutableLiveData<UserSearchState> = MutableLiveData(),
) : ViewModel() {

    fun getLiveData(): LiveData<UserSearchState> = liveData

    fun searchUsers(login: String) {
        liveData.postValue(UserSearchState.UserSearchProgress)
        retrofitRepository.searchUsers(login, object : Callback<GitHubUserSearchResult> {
            override fun onResponse(
                call: Call<GitHubUserSearchResult>,
                response: Response<GitHubUserSearchResult>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        if (result.totalCount == 0) {
                            liveData.postValue(UserSearchState.UserSearchError(
                                MessageMapper.StringResource(
                                    MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                        } else {
                            liveData.postValue(UserSearchState.UserSearchSuccess(result.items))
                        }
                    }
                } else {
                    liveData.postValue(UserSearchState.UserSearchError(
                        MessageMapper.StringResource(
                            MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            }

            override fun onFailure(call: Call<GitHubUserSearchResult>, t: Throwable) {
                liveData.postValue(UserSearchState.UserSearchError(
                    MessageMapper.DirectString(t.message.toString())))
            }
        })
    }

    fun getMostPopularUsers() {
        liveData.postValue(UserSearchState.UserSearchProgress)
        retrofitRepository.getMostPopularUsers(object :
            Callback<GitHubUserSearchResult> {
            override fun onResponse(
                call: Call<GitHubUserSearchResult>,
                response: Response<GitHubUserSearchResult>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        liveData.postValue(UserSearchState.UserSearchSuccess(result.items))
                    }
                } else {
                    liveData.postValue(UserSearchState.UserSearchError(
                        MessageMapper.StringResource(
                            MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            }

            override fun onFailure(call: Call<GitHubUserSearchResult>, t: Throwable) {
                liveData.postValue(UserSearchState.UserSearchError(
                    MessageMapper.DirectString(t.message.toString())))
            }
        })
    }
}