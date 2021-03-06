package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserSearchViewModel(private val repository: SearchUserUsecase) : ViewModel() {

    private val liveData: MutableLiveData<UserSearchState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getLiveData(): LiveData<UserSearchState> = liveData

    fun getMostPopularUsers() {
        liveData.postValue(UserSearchState.UserSearchProgress)
        compositeDisposable.add(repository.getMostPopularUsers().subscribeBy(
            onSuccess = { liveData.postValue(UserSearchState.UserSearchSuccess(it)) },
            onError = {
                liveData.postValue(it.message?.let { message ->
                    MessageMapper.DirectString(message)
                }?.let { message -> UserSearchState.UserSearchError(message) })
            }
        ))

    }

    fun searchGitHubUser(searchingRequest: String) {
        liveData.postValue(UserSearchState.UserSearchProgress)
        compositeDisposable.add(repository.searchUser(searchingRequest).subscribeBy(
            onSuccess = { searchingResult ->
                if (searchingResult.isNotEmpty()) {
                    liveData.postValue(UserSearchState.UserSearchSuccess(searchingResult))
                } else {
                    liveData.postValue(
                        UserSearchState.UserSearchError(MessageMapper.StringResource(
                            MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            },
            onError = {
                liveData.postValue(it.message?.let { message ->
                    MessageMapper.DirectString(message)
                }?.let { message -> UserSearchState.UserSearchError(message) })
            }
        ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}