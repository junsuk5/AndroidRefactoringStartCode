package com.survivalcoding.imagesearchapp.data

import com.survivalcoding.imagesearchapp.domain.PhotoRepository

class PixabayPhotoRepositoryImpl(
    private val api: PhotoApi
): PhotoRepository {

    override suspend fun fetchPhotos(query: String): List<PhotoInfo> {
        return api.getPhotos(query = query).hits
    }
}