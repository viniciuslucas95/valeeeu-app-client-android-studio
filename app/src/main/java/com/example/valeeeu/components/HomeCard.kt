package com.example.valeeeu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.models.SummaryProfile
import com.example.valeeeu.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun HomeCard(
    profile: SummaryProfile,
    size: HomeCardSize = HomeCardSize.NORMAL,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(if (size == HomeCardSize.BIG) HomeCardPictureBigWidth else HomeCardPictureNormalWidth)
            .clickable { onCardClick() }
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.barber_shop),
                contentDescription = null,
                modifier = Modifier
                    .height(if (size == HomeCardSize.BIG) HomeCardPictureBigHeight else HomeCardPictureNormalHeight),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                Box {
                    Column {
                        Text(
                            text = profile.job,
                            style = MaterialTheme.typography.overline,
                            modifier = Modifier.padding(0.dp, 22.dp, 0.dp, 12.dp)
                        )

                        Text(
                            text = profile.name, style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)
                        )

                        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)) {
                            val roundedRating = "%.1f".format(profile.averageRating).toFloat()

                            for (i in 1..5) {
                                var drawable = selectStarIcon(roundedRating, i)

                                if (i == 1) {
                                    drawable = R.drawable.ic_rating_full
                                }

                                Image(
                                    painter = painterResource(
                                        id = drawable
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(12.dp, 12.dp)
                                        .align(CenterVertically)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = formatRatingToString(profile.averageRating),
                                style = MaterialTheme.typography.caption
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                painter = painterResource(R.drawable.ic_circle),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(3.dp, 3.dp)
                                    .align(CenterVertically),
                                tint = LightGray
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = formatDistanceToString(profile.distance),
                                style = MaterialTheme.typography.caption
                            )
                        }

                        Text(
                            text = profile.description, style = MaterialTheme.typography.body1,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 12.dp)
                        )
                    }

                    Row(modifier = Modifier.padding(0.dp, 4.dp, 4.dp, 0.dp)) {
                        Spacer(modifier = Modifier.weight(1f))

                        Card(
                            shape = RoundedCornerShape(24.dp),
                            elevation = 0.dp,
                            backgroundColor = White.copy(alpha = 0f),
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .clickable { onFavoriteClick() }
                        ) {
                            Image(
                                painter = painterResource(if (profile.isFavorited) R.drawable.ic_favorite_toggled else R.drawable.ic_favorite),
                                contentDescription = null,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun selectStarIcon(rating: Float, range: Int): Int {
    if (rating >= range) {
        return R.drawable.ic_rating_full
    }


    if (rating < range && rating >= range - 0.5f) {
        return R.drawable.ic_rating_half
    }

    return R.drawable.ic_rating_empty
}

fun formatRatingToString(rating: Float): String {
    return "%.1f".format(rating)
}

fun formatDistanceToString(distance: Float): String {
    if (distance > 1000) {
        return "%.1f".format(distance / 1000) + " km"
    }

    return ((distance / 100).roundToInt() * 100).toString() + " m"
}

enum class HomeCardSize {
    BIG, NORMAL
}