package com.example.valeeeu.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.presentation.ui.theme.Red
import com.example.valeeeu.presentation.ui.theme.Yellow

const val PROFILE_CARD_BIG_HEIGHT = 186
const val PROFILE_CARD_NORMAL_HEIGHT = 154
const val PROFILE_CARD_BIG_WIDTH = 312
const val PROFILE_CARD_NORMAL_WIDTH = 224

@Composable
fun ProfileCard(size: ProfileCardSize = ProfileCardSize.NORMAL, profile: SummaryProfile) {
    ProfileCardContent(size = size, profile = profile)
}

@Composable
private fun ProfileCardContent(size: ProfileCardSize, profile: SummaryProfile) {
    val fontScale = LocalConfiguration.current.fontScale

    Card(
        modifier = Modifier
            .width(width = getWidth(size) * fontScale)
            .clickable { }
    ) {
        Column {
            PictureAndPrice(size, profile)

            Info(size, profile)
        }
    }
}

@Composable
private fun PictureAndPrice(size: ProfileCardSize, profile: SummaryProfile) {
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
                text = formatPriceText(9.90f),
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .alpha(ContentAlpha.high),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun Info(size: ProfileCardSize, profile: SummaryProfile) {
    Box {
        if (profile.isFavorited) {
            IconButton(
                drawableId = R.drawable.ic_favorite_toggled,
                onClick = { },
                color = Red,
                elevation = null,
                modifier = Modifier
                    .align(alignment = TopEnd)
                    .padding(top = 1.dp, end = 2.dp)
                    .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            )
        } else {
            val onSurfaceColor = MaterialTheme.colors.onSurface
            val disabledColor =
                Color(
                    red = onSurfaceColor.red,
                    green = onSurfaceColor.green,
                    blue = onSurfaceColor.blue,
                    alpha = ContentAlpha.disabled
                )

            IconButton(
                drawableId = R.drawable.ic_favorite,
                onClick = { },
                color = disabledColor,
                elevation = null,
                modifier = Modifier
                    .align(alignment = TopEnd)
                    .padding(top = 1.dp, end = 2.dp)
                    .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {

            Text(
                text = "Barbeiro",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .paddingFromBaseline(28.dp)
                    .padding(end = 16.dp)
                    .alpha(ContentAlpha.high)
            )

            Text(
                text = "Barbearia do Jorgin",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(33.dp)
                    .padding(end = 16.dp)
                    .alpha(ContentAlpha.high)
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
                .align(CenterVertically)
        )

        for (i in 2..5) {
            Icon(
                painter = painterResource(id = getRatingIconId(rating, i.toFloat())),
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier
                    .size(12.dp)
                    .align(CenterVertically)
            )
        }

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
                .align(CenterVertically)
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
    }
}

private fun getWidth(size: ProfileCardSize): Dp {
    return when (size) {
        ProfileCardSize.BIG -> PROFILE_CARD_BIG_WIDTH.dp
        else -> PROFILE_CARD_NORMAL_WIDTH.dp
    }
}

private fun getHeight(size: ProfileCardSize): Dp {
    return when (size) {
        ProfileCardSize.BIG -> PROFILE_CARD_BIG_HEIGHT.dp
        else -> PROFILE_CARD_NORMAL_HEIGHT.dp
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