package com.example.valeeeu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.models.SummaryProfile
import com.example.valeeeu.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun HomeCard(
    size: HomeCardSize = HomeCardSize.NORMAL,
    data: CardData?
) {
    if (data != null) {
        HomeCardData(size, data)
        return
    }

    HomeCardSkeleton(size)
}

@Composable
fun HomeCardData(size: HomeCardSize = HomeCardSize.NORMAL, data: CardData) {
    val onCardClick = data::onCardClick
    val onFavoriteClick = data::onFavoriteClick
    val profile = data.profile

    Card(
        modifier = Modifier
            .width(if (size == HomeCardSize.BIG) HomeCardPictureBigWidth else HomeCardPictureNormalWidth)
            .clickable { onCardClick() }
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.barber_shop),
                    contentDescription = null,
                    modifier = Modifier
                        .height(if (size == HomeCardSize.BIG) HomeCardPictureBigHeight else HomeCardPictureNormalHeight),
                    contentScale = ContentScale.Crop
                )

                val isDark = isSystemInDarkTheme()

                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = 0.dp,
                    backgroundColor = if (isDark) BlackAlpha90 else WhiteAlpha90,
                    modifier = Modifier
                        .align(BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = formatPriceToString(profile.lowestPrice),
                        style = MaterialTheme.typography.body2,
                        color = if (isDark) White else Black,
                        modifier = Modifier.padding(8.dp, 5.dp)
                    )
                }
            }

            Column(Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                Box {
                    Column {
                        Text(
                            text = profile.job,
                            style = MaterialTheme.typography.overline,
                            modifier = Modifier
                                .paddingFromBaseline(32.dp)
                        )

                        Text(
                            text = profile.name, style = MaterialTheme.typography.h6,
                            modifier = Modifier.paddingFromBaseline(32.dp)
                        )

                        Row(
                            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
                        ) {
                            for (i in 1..5) {
                                var drawable = selectStarIcon(profile.averageRating, i)

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
                                tint = Gray500
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
                            modifier = Modifier
                                .paddingFromBaseline(32.dp, 16.dp)
                                .padding(end = 16.dp)
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

@Composable
fun HomeCardSkeleton(size: HomeCardSize = HomeCardSize.NORMAL) {
    Card(
        modifier = Modifier
            .width(if (size == HomeCardSize.BIG) HomeCardPictureBigWidth else HomeCardPictureNormalWidth)
    ) {
        Column {
            Box {
                Box(
                    modifier = Modifier
                        .height(if (size == HomeCardSize.BIG) HomeCardPictureBigHeight else HomeCardPictureNormalHeight)
                        .fillMaxWidth()
                        .background(Gray400)
                )

                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = 0.dp,
                    backgroundColor = Gray300,
                    modifier = Modifier
                        .align(BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "",
                        color = Gray300,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(8.dp, 5.dp)
                            .width(128.dp)
                    )
                }
            }

            Column(Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                Box {
                    Column {
                        Card(
                            shape = RoundedCornerShape(4.dp),
                            elevation = 0.dp,
                            backgroundColor = Gray300,
                            modifier = Modifier
                                .paddingFromBaseline(32.dp)
                        ) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.overline,
                                color = Gray300,
                                modifier = Modifier.width(64.dp)
                            )
                        }

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            elevation = 0.dp,
                            backgroundColor = Gray300,
                            modifier = Modifier
                                .paddingFromBaseline(32.dp)
                        ) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.h6,
                                color = Gray300,
                                modifier = Modifier.width(192.dp)
                            )
                        }

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            elevation = 0.dp,
                            backgroundColor = Gray300,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        ) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.caption,
                                color = Gray300,
                                modifier = Modifier
                                    .width(128.dp)
                            )
                        }

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            elevation = 0.dp,
                            backgroundColor = Gray300,
                            modifier = Modifier
                                .paddingFromBaseline(32.dp, 16.dp)
                                .padding(end = 16.dp)
                        ) {
                            Text(
                                text = "\n\n",
                                style = MaterialTheme.typography.body1,
                                color = Gray300,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(0.dp, 4.dp, 4.dp, 0.dp)) {
                        Spacer(modifier = Modifier.weight(1f))

                        Card(
                            shape = RoundedCornerShape(24.dp),
                            elevation = 0.dp,
                            backgroundColor = White.copy(alpha = 0f),
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_favorite),
                                contentDescription = null,
                                tint = Gray400,
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
    return "%.1f".format(rating).replace(".", ",")
}

fun formatPriceToString(price: Float): String {
    if (price <= 0) return "Orçamento"

    return "A partir de R$ " + "%.2f".format(price).replace(".", ",")
}

fun formatDistanceToString(distance: Float): String {
    if (distance > 1000) {
        return "%.1f".format(distance / 1000).replace(".", ",") + " km"
    }

    return ((distance / 100).roundToInt() * 100).toString() + " m"
}

data class CardData(
    val profile: SummaryProfile,
    val onCardClick: () -> Unit,
    val onFavoriteClick: () -> Unit
)

enum class HomeCardSize {
    BIG, NORMAL
}