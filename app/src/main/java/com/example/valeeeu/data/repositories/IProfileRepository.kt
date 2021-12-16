package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.Profile

interface IProfileRepository {
    suspend fun getSummarizedProfiles(
        limit: Int,
        offset: Int,
        includeDescription:Boolean = true
    ): List<Profile>
}