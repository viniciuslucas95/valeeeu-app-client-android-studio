package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummaryProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private var count = 0

class FakeProfileRepository : IProfileRepository {
    override suspend fun getProfiles(
        limit: Int,
        offset: Int,
        fetchType: ProfileFetchType
    ): List<SummaryProfile> {
        return withContext(Dispatchers.IO) {
            delay(1000)

            val list = mutableListOf<SummaryProfile>()

            for (i in 0 until limit) {
                val profile = SummaryProfile(
                    "barbearia.carlos$count",
                    "Carlos Antônio",
                    "Barbearia",
                    "Cortamos todos os tipos de cabelo, desde os cortes mais modernos até os mais convencionais. Conosco o cliente sempre sai satisfeito, aquele cabelo sempre estiloso e bonito!",
                    3.7f,
                    800f,
                    true,
                    9.90f,
                    picture = "123"
                )

                list.add(profile)

                count++
            }

            list
        }
    }
}