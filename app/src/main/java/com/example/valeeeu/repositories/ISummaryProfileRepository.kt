package com.example.valeeeu.repositories

import com.example.valeeeu.models.SummaryProfile

interface ISummaryProfileRepository {
    suspend fun getSummaryProfile(tag: String, index: Int): SummaryProfile
}