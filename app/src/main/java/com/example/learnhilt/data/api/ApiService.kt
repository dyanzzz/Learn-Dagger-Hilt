package com.example.learnhilt.data.api

import com.example.learnhilt.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}