package com.example.valeeeu.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeTagRepository : ITagRepository {
    override suspend fun getTag(index: Int, vararg parentTags: String): String {
        return withContext(Dispatchers.IO) {
            delay(1000)
            "Barbeiro"
        }
    }
}