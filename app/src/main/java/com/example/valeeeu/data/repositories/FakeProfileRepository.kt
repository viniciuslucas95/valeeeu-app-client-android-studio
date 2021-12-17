package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.SummarizedProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private var count = 0

class FakeProfileRepository : IProfileRepository {
    override suspend fun getProfiles(
        limit: Int,
        offset: Int
    ): List<SummarizedProfile> {
        return withContext(Dispatchers.IO) {
            delay(1000)

            val list = mutableListOf<SummarizedProfile>()

            for (i in 0 until limit) {
                val profile = SummarizedProfile(
                    username = "barbearia.carlos$count",
                    name = "Carlos Antônio",
                    job = "Barbearia",
                    averageRating = 3.7f,
                    distance = 800f,
                    lowestPrice = 9.90f,
                    description = "Cortamos todos os tipos de cabelo, desde os mais modernos até os mais tradicionais. Conosco você sempre esta na moda, venha conferir!",
                    picture = "Picture string",
                    isFavorite = true
                )

                list.add(profile)

                count++
            }

            list
        }
    }
}