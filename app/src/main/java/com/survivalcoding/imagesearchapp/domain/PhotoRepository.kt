package com.survivalcoding.imagesearchapp.domain

import com.survivalcoding.imagesearchapp.data.PhotoInfo

interface PhotoRepository {

    suspend fun fetchPhotos(query: String): List<PhotoInfo>
}