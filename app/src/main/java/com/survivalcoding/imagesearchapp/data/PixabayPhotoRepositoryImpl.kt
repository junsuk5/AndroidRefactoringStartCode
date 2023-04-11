package com.survivalcoding.imagesearchapp.data

import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import javax.inject.Inject

class PixabayPhotoRepositoryImpl @Inject constructor(
    private val api: PhotoApi
): PhotoRepository {

    override suspend fun fetchPhotos(query: String): List<PhotoInfo> {
        return api.getPhotos(query = query).hits
    }
}