package com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.DataCallback
import com.ineedyourcode.githubapiapp.data.usecase.DataSearchGitHubUserUsecase
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

class UserSearchViewModel(private val repository: DataSearchGitHubUserUsecase) : ViewModel() {

    private val liveData: MutableLiveData<UserSearchState> = MutableLiveData()

    fun getLiveData(): LiveData<UserSearchState> = liveData

    fun getMostPopularUsers() {
        liveData.postValue(UserSearchState.UserSearchProgress)
        repository.getMostPopularUsers(callback)
    }

    fun searchGitHubUser(searchingRequest: String) {
        liveData.postValue(UserSearchState.UserSearchProgress)
        repository.searchUser(searchingRequest, callback)
    }

    private val callback = object :
        DataCallback<GitHubUserSearchResult> {
        override fun onSuccess(result: GitHubUserSearchResult) {
            liveData.postValue(UserSearchState.UserSearchSuccess(result.items))
        }

        override fun onFail(error: Throwable) {
            liveData.postValue(error.message?.let {
                MessageMapper.DirectString(it)
            }?.let { UserSearchState.UserSearchError(it) })
        }
    }
}