package com.example.valeeeu.data.repositories

import com.example.valeeeu.data.models.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private var count = 0

class FakeProfileRepository : IProfileRepository {
    override suspend fun getSummarizedProfiles(
        limit: Int,
        offset: Int,
        includeDescription: Boolean
    ): List<Profile> {
        return withContext(Dispatchers.IO) {
            delay(1000)

            val list = mutableListOf<Profile>()

            for (i in 0 until limit) {
                val profile = Profile(
                    username = "barbearia.carlos$count",
                    name = "Carlos Antônio",
                    job = "Barbearia",
                    averageRating = 3.7f,
                    distance = 800f,
                    isFavorite = true,
                    lowestPrice = 9.90f,
                    picture = "123",
                    description = if (includeDescription) "Cortamos todos os tipos de cabelo, desde os mais modernos até os mais tradicionais. Conosco você sempre esta na moda, venha conferir!" else null
                )

                list.add(profile)

                count++
            }

            list
        }
    }
}