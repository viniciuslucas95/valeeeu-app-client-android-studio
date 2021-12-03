package com.example.valeeeu.repositories

interface ITagRepository {
    suspend fun getTag(index: Int, vararg parentTags: String): String
}