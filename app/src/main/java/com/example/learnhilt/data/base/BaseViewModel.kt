package com.example.learnhilt.data.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<T> : ViewModel() {

    protected val _state: MutableStateFlow<T?> = MutableStateFlow(null)
    val state: StateFlow<T?>
        get() = _state

}