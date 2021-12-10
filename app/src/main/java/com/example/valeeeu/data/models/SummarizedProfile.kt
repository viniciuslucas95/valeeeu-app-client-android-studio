package com.example.valeeeu.data.models

data class SummarizedProfile(
    val username: String,
    val name: String,
    val job: String,
    val averageRating: Float,
    val distance: Float,
    val isFavorited: Boolean,
    val lowestPrice: Float,
    val picture: String?,
    val description: String?,
)