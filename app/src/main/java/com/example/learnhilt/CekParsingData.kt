package com.example.learnhilt

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnhilt.data.model.User
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat

@Composable
fun CekParsingData(
    users: List<User>,
    modifier: Modifier = Modifier,
    context: Context,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        items(users, key = { it.name }) { item ->
            Text(
                modifier = modifier
                    .clickable {
                        Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show()
                    },
                text = item.name,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colors.secondary,
            )
        }

    }
}