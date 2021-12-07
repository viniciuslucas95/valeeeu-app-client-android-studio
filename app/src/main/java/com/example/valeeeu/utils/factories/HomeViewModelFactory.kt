package com.example.valeeeu.utils.factories

import com.example.valeeeu.data.repositories.FakeProfileRepository
import com.example.valeeeu.logic.viewModels.HomeViewModel

fun homeViewModelFactory(): HomeViewModel {
    val summaryProfileRepository = FakeProfileRepository()
    return HomeViewModel(summaryProfileRepository)
}