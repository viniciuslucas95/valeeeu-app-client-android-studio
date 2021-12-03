package com.example.valeeeu.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valeeeu.components.HomeCardSection
import com.example.valeeeu.viewModels.CardSection

@Composable
fun HomeScreen(
    sections: List<CardSection>,
    onAddSection: () -> Unit,
    onAddCard: (sectionIndex: Int) -> Unit,
    onSeeMore: (sectionIndex: Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(sections) { index, section ->
                HomeCardSection(
                    section.tag,
                    section.profiles,
                    { onAddCard(index) },
                    { onSeeMore(index) })
            }
        }
    }
}