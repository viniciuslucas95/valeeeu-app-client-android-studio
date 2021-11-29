package com.example.valeeeu.models

data class SummaryProfile(
    val name: String,
    val job: String,
    val description: String,
    val averageRating: Float,
    val distance: Float,
    val isFavorited: Boolean
)
