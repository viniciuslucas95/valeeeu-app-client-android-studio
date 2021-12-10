package com.example.valeeeu.presentation.components

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.onEndReached(threshold: Int = 2): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return true

    return lastItem.index >= layoutInfo.totalItemsCount - threshold
}