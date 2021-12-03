package com.example.valeeeu.repositories

import com.example.valeeeu.models.SummaryProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeSummaryProfileRepository : ISummaryProfileRepository {
    override suspend fun getSummaryProfile(tag: String, index: Int): SummaryProfile {
        return withContext(Dispatchers.IO) {
            delay(1000)
            SummaryProfile(
                "Carlos Antônio",
                "Barbearia",
                "Cortamos todos os tipos de cabelo, desde os cortes mais modernos até os mais convencionais. Conosco o cliente sempre sai satisfeito, aquele cabelo sempre estiloso e bonito!",
                3.7f,
                800f,
                true,
                9.90f
            )
        }
    }
}