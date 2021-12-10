package com.example.valeeeu.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valeeeu.logic.viewModels.HomeViewModel
import com.example.valeeeu.presentation.sections.Categories
import com.example.valeeeu.presentation.sections.HighlightedCards
import com.example.valeeeu.utils.factories.homeViewModelFactory

@Composable
fun HomeScreen(viewModel: HomeViewModel = homeViewModelFactory()) {
    Scaffold {
        Column {
            HighlightedCards(fetchProfile = viewModel::onFetchProfile)

            Spacer(modifier = Modifier.height(height = 31.dp))

            Categories()
        }
    }
}