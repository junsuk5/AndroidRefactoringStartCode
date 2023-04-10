package com.survivalcoding.imagesearchapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.imagesearchapp.data.PhotoInfo

data class MainUiState(
    val photos: List<PhotoInfo> = emptyList(),
    val isProgress: Boolean = false,
)

class MainViewModel : ViewModel() {
    private var _state = MutableLiveData(MainUiState())
    val state: LiveData<MainUiState> = _state

    // TODO: 실제로는 오래 걸리는 처리
    fun fetchPhotos(query: String) {
        _state.value = state.value!!.copy(
            isProgress = true
        )

        Thread.sleep(2000)

        _state.value = state.value!!.copy(
            isProgress = false,
            photos = mockData,
        )
    }
}

private val mockData = listOf(
    PhotoInfo(
        id = 0,
        previewURL = "https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20210325180240_org.jpg?61e575e8653e5920470a38d1482d7312/melon/optimize/90",
        tags = "아이유, 여신"
    ),
    PhotoInfo(
        id = 1,
        previewURL = "https://image.bugsm.co.kr/album/images/500/40271/4027185.jpg",
        tags = "아이유, 가수"
    ),
    PhotoInfo(
        id = 2,
        previewURL = "https://img.gqkorea.co.kr/gq/2022/08/style_63073140eea70.jpg",
        tags = "아이유, 연기자"
    )
)