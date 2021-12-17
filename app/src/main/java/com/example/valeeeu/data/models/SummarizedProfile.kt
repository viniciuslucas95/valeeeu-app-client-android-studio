package com.example.valeeeu.data.models

data class SummarizedProfile(
    val username: String,
    val name: String,
    val job: String,
    val description: String?,
    val averageRating: Float,
    val distance: Float,
    val lowestPrice: Float,
    val picture: String?,
    val isFavorite: Boolean
)