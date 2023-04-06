package com.example.learnhilt.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnhilt.data.base.BaseViewModel
import com.example.learnhilt.data.model.User
import com.example.learnhilt.ui.main.domain.MainRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : BaseViewModel<MainViewModel.ResponseState>() {

    fun fetchUsers() {
        viewModelScope.launch {
            mainRepository.getUsers()
                .catch {
                    _state.value = ResponseState.ResponseFailed(it, "Error")
                }
                .collect { result ->
                    _state.value = result
                }
        }
    }

    sealed class ResponseState {
        object Loading : ResponseState()

        data class ResponseFailed(val error: Throwable, val isResult: String) : ResponseState()
        data class ResponseData(val data: List<User>) : ResponseState()
    }


}