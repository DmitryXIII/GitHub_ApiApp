package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserDetailsViewModel(
    private val repository: DataGetGitHubUserUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserDetailsState> = MutableLiveData()

    fun getLiveData(): LiveData<UserDetailsState> = liveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUser(login: String) {
        liveData.postValue(UserDetailsState.UserDetailsProgress)
        compositeDisposable.add(repository.getGitHubUser(login).subscribeBy(
            onSuccess = {
                liveData.postValue(UserDetailsState.UserDetailsSuccess(it))
                getUserGitHubRepositories(login)
            },
            onError = {
                liveData.postValue(UserDetailsState.UserDetailsError(
                    MessageMapper.DirectString(it.message.toString())))
            }
        ))
    }

    private fun getUserGitHubRepositories(login: String) {
        compositeDisposable.add(repository.getGitHubUserRepositoriesList(login).subscribeBy(
            onSuccess = {
                liveData.value = UserDetailsState.UserRepositoriesSuccess(it)
            },
            onError = {
                liveData.value = UserDetailsState.UserDetailsError(
                    MessageMapper.DirectString(it.message.toString()))
            }
        ))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}