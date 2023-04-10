package com.survivalcoding.imagesearchapp.data

import com.survivalcoding.imagesearchapp.domain.PhotoRepository

class PixabayPhotoRepositoryImpl: PhotoRepository {

    override suspend fun fetchPhotos(query: String): List<PhotoInfo> {
        TODO("Not yet implemented")
    }
}