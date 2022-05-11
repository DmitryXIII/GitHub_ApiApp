package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserGitHubRepositoryDetailsState
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserGitHubRepositoryViewModel(
    private val repository: GetProjectRepositoryUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserGitHubRepositoryDetailsState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getLiveData(): MutableLiveData<UserGitHubRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsProgress)
        compositeDisposable.add(repository.getProjectRepository(owner, name).subscribeBy(
            onSuccess = { gitHubRepository ->
                if (gitHubRepository.id.isNotEmpty()) {
                    liveData.postValue(
                        UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsSuccess(
                            gitHubRepository))
                } else {
                    liveData.postValue(
                        UserGitHubRepositoryDetailsState.UserGitHubRepositoryDetailsError(
                            MessageMapper.StringResource(
                                MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
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