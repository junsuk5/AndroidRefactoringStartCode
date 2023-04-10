package com.survivalcoding.imagesearchapp.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class PixabayPhotoRepositoryImplTest {

    @Test
    fun `사진 정보를 잘 가져와야 한다`() = runBlocking {
        val repository = PixabayPhotoRepositoryImpl()

        val results = repository.fetchPhotos("apple")

        assertEquals(true, results.isNotEmpty())
    }
}