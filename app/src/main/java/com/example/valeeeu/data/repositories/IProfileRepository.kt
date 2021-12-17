package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummarizedProfile

interface IProfileRepository {
    suspend fun getProfiles(
        limit: Int,
        offset: Int
    ): List<SummarizedProfile>
}