package com.survivalcoding.imagesearchapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.survivalcoding.imagesearchapp.ui.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ImageSearchScreen(
    viewModel: MainViewModel = viewModel()
) {
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "이미지 검색")
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "검색 버튼",
                        )
                    }
                }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(listOf(1, 2, 3, 4)) { photo ->
                    Text(text = "$photo")
                }
            }
        }
    }
}