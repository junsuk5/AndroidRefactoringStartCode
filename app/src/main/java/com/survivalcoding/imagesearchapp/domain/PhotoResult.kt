package com.survivalcoding.imagesearchapp.domain

data class PhotoResult(
    val total: Int,
    val totalHits: Int,
    val hits: List<PhotoInfo>,
)