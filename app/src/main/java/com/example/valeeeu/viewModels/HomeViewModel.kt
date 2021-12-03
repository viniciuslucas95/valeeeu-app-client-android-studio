package com.example.valeeeu.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.valeeeu.models.SummaryProfile
import com.example.valeeeu.repositories.ISummaryProfileRepository
import com.example.valeeeu.repositories.ITagRepository

class HomeViewModel(
    private val summaryProfileRepository: ISummaryProfileRepository,
    private val tagRepository: ITagRepository
) : ViewModel() {
    val sections = mutableStateOf<List<CardSection>>(listOf())

    init {
        addSection()
    }

    fun addSection() {

    }

    fun addCard(sectionIndex: Int) {

    }

    fun seeMore(sectionIndex: Int) {

    }
}

data class CardSection(
    var tag: String?,
    var profiles: List<SummaryProfile?>
)