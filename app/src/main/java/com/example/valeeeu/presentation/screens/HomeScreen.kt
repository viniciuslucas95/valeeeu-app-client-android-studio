package com.example.valeeeu.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
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
import androidx.navigation.NavController
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.logic.viewModels.HomeViewModel
import com.example.valeeeu.presentation.navigation.BottomNavBar
import com.example.valeeeu.presentation.components.PROFILE_CARD_COMPACT_HEIGHT
import com.example.valeeeu.presentation.components.ProfileCardCompact
import com.example.valeeeu.presentation.components.onEndReached
import com.example.valeeeu.presentation.sections.Categories
import com.example.valeeeu.presentation.sections.HighlightedCards
import com.example.valeeeu.utils.factories.homeViewModelFactory
import kotlin.math.ceil

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = homeViewModelFactory()) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val suggestedProfiles = remember { mutableStateOf(listOf<SummarizedProfile>()) }
    val maxVerticalCardsInScreen =
        remember { ceil(screenHeight / PROFILE_CARD_COMPACT_HEIGHT.dp).toInt() }
    val suggestedListState = rememberLazyListState()
    val isSuggestedListFetching = remember { mutableStateOf(false) }

    if (suggestedListState.onEndReached(maxVerticalCardsInScreen) && !isSuggestedListFetching.value) {
        isSuggestedListFetching.value = true

        val fetchAmount =
            if (suggestedProfiles.value.isEmpty()) maxVerticalCardsInScreen * 2 else maxVerticalCardsInScreen

        viewModel.onFetchProfile(
            fetchAmount,
            suggestedProfiles.value.size,
            false
        ) {
            suggestedProfiles.value = suggestedProfiles.value + it

            isSuggestedListFetching.value = false
        }
    }

    HomeScreenContent(
        navController = navController,
        viewModel = viewModel,
        suggestedListState = suggestedListState,
        suggestedProfiles = suggestedProfiles.value,
        isSuggestedListFetching = isSuggestedListFetching.value
    )
}

@Composable
private fun HomeScreenContent(
    navController: NavController,
    viewModel: HomeViewModel,
    suggestedProfiles: List<SummarizedProfile>,
    suggestedListState: LazyListState,
    isSuggestedListFetching: Boolean
) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navController) }
    ) {
        LazyColumn(
            state = suggestedListState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                HighlightedCards(fetchProfile = viewModel::onFetchProfile)

                Spacer(modifier = Modifier.height(height = 31.dp))
            }

            item {
                Categories()

                Spacer(modifier = Modifier.height(height = 24.dp))
            }

            // Suggested list section starts here
            // If possible, move it to its own file
            item {
                SuggestedListTitle()

                Spacer(modifier = Modifier.height(height = 12.dp))
            }

            if (suggestedProfiles.isEmpty()) {
                item {
                    EmptySuggestedListCircularProgressIndicator()
                }
            }

            itemsIndexed(
                suggestedProfiles,
                key = { _, profile -> profile.username }) { index, profile ->
                SuggestedCard(profile = profile, isNotLast = index < suggestedProfiles.size - 1)
            }

            if (isSuggestedListFetching && suggestedProfiles.isNotEmpty()) {
                item {
                    SuggestedListCircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun EmptySuggestedListCircularProgressIndicator() {
    Row(
        modifier = Modifier.height(height = PROFILE_CARD_COMPACT_HEIGHT.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        CircularProgressIndicator()

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SuggestedListCircularProgressIndicator() {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        Spacer(modifier = Modifier.weight(1f))

        CircularProgressIndicator()

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SuggestedCard(profile: SummarizedProfile, isNotLast: Boolean) {
    ProfileCardCompact(profile = profile)

    if (isNotLast) {
        Spacer(modifier = Modifier.height(height = 16.dp))
    }
}

@Composable
private fun SuggestedListTitle() {
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

@Composable
private fun TopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        title = {
            Text(
                text = stringResource(R.string.home),
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
    )
}