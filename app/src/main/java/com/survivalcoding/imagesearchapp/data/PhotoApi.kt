package com.survivalcoding.imagesearchapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// TODO: Retrofit 적용
interface PhotoApi {

    @GET("api/?image_type=photo")
    fun getPhotos(
        @Query("key") key: String = "10711147-dc41758b93b263957026bdadb",
        @Query("q") query: String,
    ): Call<PhotoResult>
}