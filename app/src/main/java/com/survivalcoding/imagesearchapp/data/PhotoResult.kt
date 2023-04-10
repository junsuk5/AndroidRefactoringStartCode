package com.survivalcoding.imagesearchapp.data

data class PhotoResult(
    val total: Int,
    val totalHits: Int,
    val hits: List<PhotoInfo>,
)