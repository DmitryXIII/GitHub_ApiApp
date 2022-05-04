package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserDetailsState {
    object UserDetailsProgress : UserDetailsState()

    data class UserDetailsSuccess(val user: UserProfile) : UserDetailsState()

    data class UserRepositoriesSuccess(
        val repositoriesList: List<UserProjectRepository>,
    ) : UserDetailsState()

    data class UserDetailsError(val error: MessageMapper) : UserDetailsState()
}