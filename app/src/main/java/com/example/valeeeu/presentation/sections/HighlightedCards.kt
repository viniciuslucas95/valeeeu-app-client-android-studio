package com.example.valeeeu.presentation.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.data.repositories.ProfileFetchType
import com.example.valeeeu.logic.formatters.formatRatingText
import com.example.valeeeu.presentation.components.*
import com.example.valeeeu.presentation.ui.theme.Black
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
    val maxCardsInScreen =
        remember { ceil(screenWidth / PROFILE_CARD_HORIZONTAL_NORMAL_WIDTH.dp).toInt() }
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
private fun HighlightedCardsContent(
    profiles: List<SummaryProfile>,
    listState: LazyListState,
    isFetching: Boolean
) {
    Column(
        modifier = Modifier.paddingFromBaseline(top = 40.dp)
    ) {
        Row(modifier = Modifier.paddingFromBaseline(bottom = 16.dp)) {
            Text(
                text = stringResource(R.string.highlights),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignBy(LastBaseline)
                    .alpha(ContentAlpha.high)
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                text = stringResource(R.string.see_more),
                enabled = profiles.isNotEmpty(),
                onClick = { },
                modifier = Modifier
                    .alignBy(LastBaseline)
            )
        }

        if (profiles.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                EmptyCardForMeasurement()

                CircularProgressIndicator()
            }
        }

        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(profiles, key = { _, profile -> profile.username }) { index, profile ->
                ProfileCardHorizontal(profile = profile, size = ProfileCardSize.NORMAL)

                if (index < profiles.size - 1) {
                    Spacer(modifier = Modifier.width(width = 16.dp))
                }
            }

            if (isFetching && profiles.isNotEmpty()) {
                item {
                    Box(modifier = Modifier.padding(start = 16.dp)) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyCardForMeasurement(){
    Column {
        Spacer(modifier = Modifier.height(height = PROFILE_CARD_HORIZONTAL_NORMAL_HEIGHT.dp))

        Text(
            text = "",
            style = MaterialTheme.typography.overline,
            modifier = Modifier.paddingFromBaseline(28.dp)
        )

        Text(
            text = "",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.paddingFromBaseline(33.dp)
        )

        Row(
            modifier = Modifier.paddingFromBaseline(top = 15.dp)
        ) {
            Text(
                text = "",
                style = MaterialTheme.typography.caption
            )
        }

        Text(
            text = "\n\n",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .paddingFromBaseline(top = 29.dp, bottom = 16.dp)
        )
    }
}