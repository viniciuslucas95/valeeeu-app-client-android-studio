package com.example.valeeeu.presentation.components

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.onEndReached(remainingIndexToFetch: Int): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return true

    return lastItem.index >= layoutInfo.totalItemsCount - remainingIndexToFetch
}