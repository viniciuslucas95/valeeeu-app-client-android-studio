package com.example.valeeeu.data.models

data class Profile(
    val username: String,
    val name: String,
    val job: String,
    val averageRating: Float,
    val distance: Float,
    val isFavorite: Boolean,
    val lowestPrice: Float,
    val picture: String?,
    val description: String?,
)