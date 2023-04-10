package com.survivalcoding.imagesearchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.imagesearchapp.data.MockPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.data.PhotoInfo
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import kotlinx.coroutines.launch

data class MainUiState(
    val photos: List<PhotoInfo> = emptyList(),
    val isProgress: Boolean = false,
)

class MainViewModel(
    private val photoRepository: PhotoRepository = MockPhotoRepositoryImpl()
) : ViewModel() {

    private var _state = MutableLiveData(MainUiState())
    val state: LiveData<MainUiState> = _state

    // 실제로는 오래 걸리는 처리
    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            _state.value = state.value!!.copy(
                isProgress = true,
                photos = emptyList(),
            )

            _state.value = state.value!!.copy(
                isProgress = false,
                photos = photoRepository.fetchPhotos(query),
            )
        }
    }
}