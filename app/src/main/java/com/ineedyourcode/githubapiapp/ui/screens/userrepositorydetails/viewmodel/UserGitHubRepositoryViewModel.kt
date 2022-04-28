package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserGitHubRepositoryDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserGitHubRepositoryViewModel(
    private val repository: DataGetGitHubRepositoryUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserGitHubRepositoryDetailsState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getLiveData(): MutableLiveData<UserGitHubRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress)
        compositeDisposable.add(repository.getGitHubRepository(owner, name).subscribeBy(
            onSuccess = {
                liveData.postValue(
                    UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsSuccess(it))
            },
            onError = {
                liveData.postValue(
                    UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError(
                        MessageMapper.DirectString(it.message.toString())))
            }
        ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}