package com.survivalcoding.imagesearchapp

import android.app.Application
import com.survivalcoding.imagesearchapp.data.PhotoApi
import com.survivalcoding.imagesearchapp.data.PixabayPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// TODO: DI 라이브러리 사용
class ImageSearchApp : Application() {
    private val api: PhotoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    val photoRepository: PhotoRepository by lazy {
        PixabayPhotoRepositoryImpl(api)
//        MockPhotoRepositoryImpl()
    }
}