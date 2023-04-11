package com.survivalcoding.imagesearchapp.domain

interface PhotoRepository {

    suspend fun fetchPhotos(query: String): List<PhotoInfo>
}