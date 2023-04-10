package com.survivalcoding.imagesearchapp

import android.app.Application
import com.survivalcoding.imagesearchapp.data.MockPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.data.PixabayPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.domain.PhotoRepository

class ImageSearchApp : Application() {

    val photoRepository: PhotoRepository by lazy {
        PixabayPhotoRepositoryImpl()
//        MockPhotoRepositoryImpl()
    }
}