package com.example.valeeeu.presentation.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.data.repositories.ProfileFetchType
import com.example.valeeeu.presentation.components.*
import kotlin.math.ceil

@Composable
fun HighlightedCards(
    fetchProfile: (
        limit: Int,
        offset: Int,
        fetchType: ProfileFetchType,
        callback: (List<SummaryProfile>) -> Unit
    ) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val profiles = remember { mutableStateOf(listOf<SummaryProfile>()) }
    val maxCardsInScreen = remember { ceil(screenWidth / PROFILE_CARD_NORMAL_WIDTH.dp).toInt() }
    val listState = rememberLazyListState()
    val isFetching = remember { mutableStateOf(false) }

    if (listState.onEndReached() && !isFetching.value) {
        isFetching.value = true

        val fetchAmount = if (profiles.value.isEmpty()) maxCardsInScreen * 2 else maxCardsInScreen

        fetchProfile(
            fetchAmount,
            profiles.value.size,
            ProfileFetchType.SUMMARY
        ) {
            profiles.value = profiles.value + it

            isFetching.value = false
        }
    }

    HighlightedCardsContent(
        profiles = profiles.value,
        listState = listState,
        isFetching = isFetching.value
    )
}

@Composable
fun HighlightedCardsContent(
    profiles: List<SummaryProfile>,
    listState: LazyListState,
    isFetching: Boolean
) {
    Column(
        modifier = Modifier.paddingFromBaseline(top = 40.dp)
    ) {
        Row(modifier = Modifier.paddingFromBaseline(bottom = 16.dp)) {
            Text(
                text = "Destaques",
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignBy(LastBaseline)
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                text = "Ver Mais",
                onClick = { },
                modifier = Modifier.alignBy(LastBaseline)
            )
        }

        if (profiles.isEmpty()) {
            Row(
                modifier = Modifier.height(height = PROFILE_CARD_NORMAL_HEIGHT.dp * 2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                CircularProgressIndicator()

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(profiles, key = { _, profile -> profile.username }) { index, profile ->
                ProfileCard(profile = profile, size = ProfileCardSize.NORMAL)

                if (index < profiles.size - 1) {
                    Spacer(modifier = Modifier.width(width = 16.dp))
                }
            }

            if (isFetching && profiles.isNotEmpty()) {
                item {
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

const val ON_END_THRESHOLD = 2

private fun LazyListState.onEndReached(): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return true

    return lastItem.index >= layoutInfo.totalItemsCount - ON_END_THRESHOLD
}