package com.survivalcoding.imagesearchapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("api/?image_type=photo&key=10711147-dc41758b93b263957026bdadb")
    suspend fun getPhotos(
        @Query("q") query: String,
    ): PhotoResult
}