package com.example.learnhilt

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.learnhilt.data.model.User

@Composable
fun SectionParsingData (
    modifier: Modifier = Modifier,
    state: List<User>,
    context: Context
) {
    Column(modifier) {
        CekParsingData(
            modifier = modifier,
            users = state,
            context = context
        )
    }
}