package com.example.learnhilt

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import com.example.learnhilt.data.model.User
import com.example.learnhilt.ui.main.MainViewModel
import com.example.learnhilt.utils.Response

@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
) {
    fun launch() {
        viewModel.fetchUsers()
    }

    launch()
    viewModel.state.collectAsState(initial = MainViewModel.ResponseState.Loading).value.let { response ->
        when (response) {
            is MainViewModel.ResponseState.Loading -> {
                println("LOADING")
            }
            is MainViewModel.ResponseState.ResponseFailed -> {
                println("ERROR")
                println("${response.error}, ${response.isResult}")
            }
            is MainViewModel.ResponseState.ResponseData -> {
                println("RESPONSE DATA")

                SectionParsingData(state = response.data, context = context)

                println(response.data.toString())
            }
            else -> {}
        }
    }
}
