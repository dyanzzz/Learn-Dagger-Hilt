package com.example.learnhilt.ui.main.domain

import com.example.learnhilt.data.api.ApiService
import com.example.learnhilt.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiHelper: ApiService) {

    fun getUsers(): Flow<MainViewModel.ResponseState> = flow {
        try {
            val responseApi = apiHelper.getUsers()
            emit(MainViewModel.ResponseState.ResponseData(responseApi))
        } catch (err: Exception) {
            emit(MainViewModel.ResponseState.ResponseFailed(err, "Error"))
        }
    }.flowOn(Dispatchers.IO)
}
