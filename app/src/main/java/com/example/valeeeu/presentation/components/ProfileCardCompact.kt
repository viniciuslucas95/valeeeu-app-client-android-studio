package com.example.valeeeu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.logic.formatters.formatDistanceText
import com.example.valeeeu.logic.formatters.formatPriceText
import com.example.valeeeu.logic.formatters.formatRatingText
import com.example.valeeeu.presentation.ui.theme.Yellow

const val PROFILE_CARD_COMPACT_HEIGHT = 84

@Composable
fun ProfileCardCompact(profile: SummarizedProfile) {
    ProfileCardCompactContent(profile)
}

@Composable
private fun ProfileCardCompactContent(profile: SummarizedProfile) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { }
    ) {
        Box {
            FavoriteButton(
                isFavorited = profile.isFavorited,
                modifier = Modifier.align(alignment = Alignment.TopEnd)
            )

            Row {
                Image(
                    // Change to get picture from string
                    painter = painterResource(id = R.drawable.barber_shop),
                    contentDescription = null,
                    modifier = Modifier
                        .width(width = PROFILE_CARD_COMPACT_HEIGHT.dp)
                        .height(height = PROFILE_CARD_COMPACT_HEIGHT.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = profile.job,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.overline,
                        modifier = Modifier
                            .paddingFromBaseline(20.dp)
                            .padding(end = 16.dp)
                            .alpha(ContentAlpha.high)
                    )

                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .paddingFromBaseline(25.dp)
                            .padding(end = 48.dp)
                            .alpha(ContentAlpha.high)
                    )

                    RatingDistanceAndPrice(
                        rating = profile.averageRating,
                        distance = profile.distance,
                        price = profile.lowestPrice
                    )
                }
            }
        }
    }
}

@Composable
private fun RatingDistanceAndPrice(rating: Float, distance: Float, price: Float) {
    Row(
        modifier = Modifier
            .paddingFromBaseline(top = 15.dp)
            .padding(end = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rating_full),
            contentDescription = null,
            tint = Yellow,
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatRatingText(rating),
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            modifier = Modifier
                .alpha(ContentAlpha.disabled)
                .alignBy(LastBaseline)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier
                .size(2.dp)
                .align(Alignment.CenterVertically)
                .alpha(ContentAlpha.disabled)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatDistanceText(distance),
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            modifier = Modifier
                .alpha(ContentAlpha.disabled)
                .alignBy(LastBaseline)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier
                .size(2.dp)
                .align(Alignment.CenterVertically)
                .alpha(ContentAlpha.disabled)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatPriceText(
                price = price,
                context = LocalContext.current,
                summarized = true
            ),
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            modifier = Modifier
                .alpha(ContentAlpha.disabled)
                .alignBy(LastBaseline)
        )
    }
}
