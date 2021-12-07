package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummaryProfile

interface IProfileRepository {
    suspend fun getSummaryProfile(): SummaryProfile
}