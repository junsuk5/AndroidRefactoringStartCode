package com.survivalcoding.imagesearchapp

import android.app.Application
import com.survivalcoding.imagesearchapp.data.PhotoApi
import com.survivalcoding.imagesearchapp.data.PixabayPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@HiltAndroidApp
class ImageSearchApp : Application()