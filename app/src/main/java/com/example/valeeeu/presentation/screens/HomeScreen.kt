package com.example.valeeeu.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.data.repositories.ProfileFetchType
import com.example.valeeeu.logic.viewModels.HomeViewModel
import com.example.valeeeu.presentation.components.PROFILE_CARD_HORIZONTAL_NORMAL_HEIGHT
import com.example.valeeeu.presentation.components.PROFILE_CARD_VERTICAL_HEIGHT
import com.example.valeeeu.presentation.components.ProfileCardVertical
import com.example.valeeeu.presentation.components.onEndReached
import com.example.valeeeu.presentation.sections.Categories
import com.example.valeeeu.presentation.sections.HighlightedCards
import com.example.valeeeu.utils.factories.homeViewModelFactory
import kotlin.math.ceil

@Composable
fun HomeScreen(viewModel: HomeViewModel = homeViewModelFactory()) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val suggestedProfiles = remember { mutableStateOf(listOf<SummaryProfile>()) }
    val maxVerticalCardsInScreen =
        remember { ceil(screenHeight / PROFILE_CARD_VERTICAL_HEIGHT.dp).toInt() }
    val suggestedListState = rememberLazyListState()
    val isFetching = remember { mutableStateOf(false) }

    if (suggestedListState.onEndReached(threshold = maxVerticalCardsInScreen) && !isFetching.value) {
        isFetching.value = true

        val fetchAmount =
            if (suggestedProfiles.value.isEmpty()) maxVerticalCardsInScreen * 2 else maxVerticalCardsInScreen

        viewModel.onFetchProfile(
            fetchAmount,
            suggestedProfiles.value.size,
            ProfileFetchType.SUMMARY
        ) {
            suggestedProfiles.value = suggestedProfiles.value + it

            isFetching.value = false
        }
    }

    HomeScreenContent(
        viewModel = viewModel,
        suggestedListState = suggestedListState,
        suggestedProfiles = suggestedProfiles.value,
        isSuggestedListFetching = isFetching.value
    )
}

@Composable
private fun HomeScreenContent(
    viewModel: HomeViewModel,
    suggestedProfiles: List<SummaryProfile>,
    suggestedListState: LazyListState,
    isSuggestedListFetching: Boolean
) {
    Scaffold {
        LazyColumn(
            state = suggestedListState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                HighlightedCards(fetchProfile = viewModel::onFetchProfile)
            }

            item {
                Spacer(modifier = Modifier.height(height = 31.dp))
            }

            item {
                Categories()
            }

            item {
                Spacer(modifier = Modifier.height(height = 24.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.suggestions),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .alpha(ContentAlpha.high)
                )
            }

            item {
                Spacer(modifier = Modifier.height(height = 12.dp))
            }

            if (suggestedProfiles.isEmpty()) {
                item {
                    Row(
                        modifier = Modifier.height(height = PROFILE_CARD_VERTICAL_HEIGHT.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        CircularProgressIndicator()

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            itemsIndexed(
                suggestedProfiles,
                key = { _, profile -> profile.username }) { index, profile ->
                ProfileCardVertical(profile = profile)

                if (index < suggestedProfiles.size - 1) {
                    Spacer(modifier = Modifier.height(height = 16.dp))
                }
            }

            if (isSuggestedListFetching && suggestedProfiles.isNotEmpty()) {
                item {
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Spacer(modifier = Modifier.weight(1f))

                        CircularProgressIndicator()

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}