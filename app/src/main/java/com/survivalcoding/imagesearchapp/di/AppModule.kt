package com.survivalcoding.imagesearchapp.di

import com.survivalcoding.imagesearchapp.data.PhotoApi
import com.survivalcoding.imagesearchapp.data.PixabayPhotoRepositoryImpl
import com.survivalcoding.imagesearchapp.domain.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePhotoApi() : PhotoApi {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    fun providePhotoRepository(api: PhotoApi) : PhotoRepository {
        return PixabayPhotoRepositoryImpl(api)
    }
}