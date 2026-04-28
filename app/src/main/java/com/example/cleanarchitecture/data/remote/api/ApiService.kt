package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.data.remote.api.ApiConstant.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(USERS)
    suspend fun getUsers(@Query("page") page: Int): ArrayList<User>
}
