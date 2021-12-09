package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummaryProfile

interface IProfileRepository {
    suspend fun getProfiles(
        limit: Int = 10,
        offset: Int = 0,
        fetchType: ProfileFetchType = ProfileFetchType.LIST
    ): List<SummaryProfile>
}

enum class ProfileFetchType {
    COMPLETE, SUMMARY, LIST
}