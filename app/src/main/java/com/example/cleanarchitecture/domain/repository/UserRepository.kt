package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.User

interface UserRepository {
    suspend fun getUsers(page: Int): ArrayList<User>
}