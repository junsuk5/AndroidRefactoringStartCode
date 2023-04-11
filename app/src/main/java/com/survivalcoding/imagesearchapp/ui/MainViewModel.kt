package com.survivalcoding.imagesearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.imagesearchapp.ImageSearchApp
import com.survivalcoding.imagesearchapp.data.PhotoInfo
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val photos: List<PhotoInfo> = emptyList(),
    val isProgress: Boolean = false,
    val message: String? = null,
)

sealed class MainEvent {
    data class ShowMessage(val message: String): MainEvent()
    object EndLoading : MainEvent()
}

class MainViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private var _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    private var _eventFlow = MutableSharedFlow<MainEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    // 실제로는 오래 걸리는 처리
    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                _eventFlow.emit(MainEvent.ShowMessage("검색어를 입력하세요"))
                return@launch
            }

            try {
                _state.value = state.value.copy(
                    isProgress = true,
                    photos = emptyList(),
                )

                _state.value = state.value.copy(
                    isProgress = false,
                    photos = photoRepository.fetchPhotos(query),
                )
                _eventFlow.emit(MainEvent.EndLoading)
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    isProgress = false
                )
                _eventFlow.emit(MainEvent.ShowMessage("네트워크 에러 : ${e.message}"))
            }
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