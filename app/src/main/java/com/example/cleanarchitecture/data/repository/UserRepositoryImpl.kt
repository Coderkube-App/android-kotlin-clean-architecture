package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.data.remote.api.ApiService
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(page: Int): ArrayList<User> = apiService.getUsers(page)
}