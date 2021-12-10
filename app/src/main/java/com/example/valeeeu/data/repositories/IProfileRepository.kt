package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummarizedProfile

interface IProfileRepository {
    suspend fun getSummarizedProfiles(
        limit: Int,
        offset: Int,
        includeDescription:Boolean = true
    ): List<SummarizedProfile>
}