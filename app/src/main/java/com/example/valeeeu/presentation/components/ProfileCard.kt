package com.example.valeeeu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.presentation.ui.theme.Black
import com.example.valeeeu.presentation.ui.theme.Red
import com.example.valeeeu.presentation.ui.theme.Yellow

const val BIG_HEIGHT = 186
const val NORMAL_HEIGHT = 154
const val BIG_WIDTH = 312
const val NORMAL_WIDTH = 224

@Composable
fun ProfileCard(size: ProfileCardSize = ProfileCardSize.NORMAL, profile: SummaryProfile? = null) {
    Scaffold {
        Card(modifier = Modifier
            .width(width = getWidth(size))
            .clickable { }
        ) {
            Column {
                PictureAndPrice(size, profile)

                Info(size)
            }
        }
    }
}

@Composable
private fun PictureAndPrice(size: ProfileCardSize, profile: SummaryProfile?) {
    Box {
        Image(
            // Change to get picture from string
            painter = painterResource(id = R.drawable.barber_shop),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = getHeight(size)),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier
                .padding(end = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.BottomEnd)
                .alpha(ContentAlpha.high),
            shape = RoundedCornerShape(100)
        ) {
            Text(
                text = if (profile != null) formatPriceText(profile.lowestPrice) else "",
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun Info(size: ProfileCardSize) {
    Box {
        Surface(
            modifier = Modifier
                .align(alignment = TopEnd)
                .padding(top = 1.dp, end = 2.dp)
                .clip(RoundedCornerShape(100))
                .clickable { }
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_favorite
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .alpha(ContentAlpha.disabled)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Barbeiro",
                style = MaterialTheme.typography.overline,
                modifier = Modifier.paddingFromBaseline(28.dp)
            )

            Text(
                text = "Barbearia do Jorgin",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(33.dp)
                    .padding(end = 16.dp)
            )

            RatingAndDistance(rating = 1.332123f, distance = 1231.3213f)

            Text(
                text = "Cortamos todos os tipos de cabelo, desde os cortes mais modernos até os mais tradicionais. Conosco você sempre estará no estilo, as gatas cairão aos seus pés.",
                style = when (size) {
                    ProfileCardSize.NORMAL -> MaterialTheme.typography.body2
                    else -> MaterialTheme.typography.body1
                },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(top = 29.dp, bottom = 16.dp)
                    .padding(end = 16.dp)
                    .alpha(ContentAlpha.medium)
            )
        }
    }
}

@Composable
private fun RatingAndDistance(rating: Float, distance: Float) {
    Row(
        modifier = Modifier.paddingFromBaseline(top = 15.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rating_full),
            contentDescription = null,
            tint = Yellow,
            modifier = Modifier
                .size(12.dp)
                .align(alignment = CenterVertically)
        )

        for (i in 2..5) {
            Icon(
                painter = painterResource(id = getRatingIconId(rating, i.toFloat())),
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier
                    .size(12.dp)
                    .align(alignment = CenterVertically)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatRatingText(rating),
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .alpha(ContentAlpha.disabled)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier
                .size(2.dp)
                .align(alignment = CenterVertically)
                .alpha(ContentAlpha.disabled)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatDistanceText(distance),
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .alpha(ContentAlpha.disabled)
        )
    }
}

private fun getWidth(size: ProfileCardSize): Dp {
    return when (size) {
        ProfileCardSize.BIG -> BIG_WIDTH.dp
        else -> NORMAL_WIDTH.dp
    }
}

private fun getHeight(size: ProfileCardSize): Dp {
    return when (size) {
        ProfileCardSize.BIG -> BIG_HEIGHT.dp
        else -> NORMAL_HEIGHT.dp
    }
}

private fun formatPriceText(price: Float): String {
    val formattedPrice = "%.2f".format(price).replace(".", ",")
    return "A partir de R$ $formattedPrice"
}

private fun getRatingIconId(rating: Float, range: Float): Int {
    return when (rating) {
        in range..Float.MAX_VALUE -> R.drawable.ic_rating_full
        in range - 0.5f..range -> R.drawable.ic_rating_half
        else -> R.drawable.ic_rating_empty
    }
}

private fun formatRatingText(rating: Float): String {
    return "%.1f".format(rating).replace(".", ",")
}

private fun formatDistanceText(distance: Float): String {
    return when (distance) {
        in 1000f..Float.MAX_VALUE -> "%.1f".format((distance / 1000)).replace(".", ",") + " km"
        else -> "%.0f".format(distance).replace(".", ",") + " m"
    }
}

enum class ProfileCardSize {
    NORMAL, BIG
}