package com.ineedyourcode.githubapiapp.domain.repository

import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase

interface UsecaseRepository : SearchUserUsecase, GetUserUsecase, GetProjectRepositoryUsecase {
}