package com.survivalcoding.imagesearchapp.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.imagesearchapp.ui.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun ImageSearchScreen(
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                        onClick = {
                            viewModel.fetchPhotos(query)
                        }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "검색 버튼",
                        )
                    }
                }
            )

            if (state.isProgress) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(state.photos) { photo ->
                        AsyncImage(
                            model = photo.previewURL,
                            contentDescription = photo.tags,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.aspectRatio(1f)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                }
            }
        }
    }
}