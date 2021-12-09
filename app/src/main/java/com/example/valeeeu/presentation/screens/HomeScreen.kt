package com.example.valeeeu.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.valeeeu.logic.viewModels.HomeViewModel
import com.example.valeeeu.presentation.sections.HighlightedCards
import com.example.valeeeu.utils.factories.homeViewModelFactory

@Composable
fun HomeScreen(viewModel: HomeViewModel = homeViewModelFactory()) {
    Scaffold {
        HighlightedCards(fetchProfile = viewModel::onFetchProfile)
    }
}