package com.survivalcoding.imagesearchapp.data

import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PixabayPhotoRepositoryImpl: PhotoRepository {
    private val api: PhotoApi = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()

    override suspend fun fetchPhotos(query: String): List<PhotoInfo> {
        return api.getPhotos(query = query).hits
    }
}