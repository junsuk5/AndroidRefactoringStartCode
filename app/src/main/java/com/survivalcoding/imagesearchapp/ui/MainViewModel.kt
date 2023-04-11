package com.survivalcoding.imagesearchapp.ui

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.imagesearchapp.ImageSearchApp
import com.survivalcoding.imagesearchapp.data.PhotoInfo
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class MainUiState(
    val photos: List<PhotoInfo> = emptyList(),
    val isProgress: Boolean = false,
)

class MainViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private var _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    // TODO : 이벤트, 에러 처리

    // 실제로는 오래 걸리는 처리
    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            // TODO : query 예외 처리

            // TODO : 네트워크 예외 처리
            _state.value = state.value.copy(
                isProgress = true,
                photos = emptyList(),
            )

            _state.value = state.value.copy(
                isProgress = false,
                photos = photoRepository.fetchPhotos(query),
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val photoRepository = (this[APPLICATION_KEY] as ImageSearchApp).photoRepository
                MainViewModel(
                    photoRepository = photoRepository
                )
            }
        }
    }
}