package com.example.valeeeu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.logic.formatters.formatDistanceText
import com.example.valeeeu.logic.formatters.formatPriceText
import com.example.valeeeu.logic.formatters.formatRatingText
import com.example.valeeeu.presentation.ui.theme.Yellow

const val PROFILE_CARD_BIG_HEIGHT = 186
const val PROFILE_CARD_NORMAL_HEIGHT = 154
const val PROFILE_CARD_BIG_WIDTH = 312
const val PROFILE_CARD_NORMAL_WIDTH = 224

@Composable
fun ProfileCard(size: ProfileCardSize = ProfileCardSize.NORMAL, profile: SummarizedProfile) {
    ProfileCardContent(size = size, profile = profile)
}

@Composable
private fun ProfileCardContent(size: ProfileCardSize, profile: SummarizedProfile) {
    val fontScale = LocalConfiguration.current.fontScale - 1
    val adjust = if (fontScale > 0) fontScale * 0.5f + 1 else 1f

    Card(
        modifier = Modifier
            .width(width = getWidth(size) * adjust)
            .clickable { }
    ) {
        Column {
            PictureAndPrice(size, profile)

            Info(size, profile)
        }
    }
}

@Composable
private fun PictureAndPrice(size: ProfileCardSize, profile: SummarizedProfile) {
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
            shape = RoundedCornerShape(percent = 100)
        ) {
            Text(
                text = formatPriceText(price = profile.lowestPrice, context = LocalContext.current),
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
private fun Info(size: ProfileCardSize, profile: SummarizedProfile) {
    Box {
        FavoriteButton(
            isFavorite = profile.isFavorite,
            modifier = Modifier.align(alignment = Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = profile.job,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .paddingFromBaseline(28.dp)
                    .padding(end = 16.dp)
                    .alpha(ContentAlpha.high)
            )

            Text(
                text = profile.name,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(33.dp)
                    .padding(end = 16.dp)
                    .alpha(ContentAlpha.high)
            )

            RatingAndDistance(rating = profile.averageRating, distance = profile.distance)

            Text(
                text = profile.description ?: "",
                style = when (size) {
                    ProfileCardSize.NORMAL -> MaterialTheme.typography.body2
                    else -> MaterialTheme.typography.body1
                },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .paddingFromBaseline(top = 29.dp, bottom = 16.dp)
                    .padding(end = 16.dp)
                    .alpha(alpha = ContentAlpha.medium)
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
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = Yellow,
            modifier = Modifier
                .size(12.dp)
                .align(CenterVertically)
        )

        for (i in 2..5) {
            Icon(
                imageVector = getRatingIconId(rating, i.toFloat()),
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
            imageVector = Icons.Rounded.Circle,
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

private fun getRatingIconId(rating: Float, range: Float): ImageVector {
    return when (rating) {
        in range..Float.MAX_VALUE -> Icons.Rounded.Star
        in range - 0.5f..range -> Icons.Rounded.StarHalf
        else -> Icons.Rounded.StarBorder
    }
}

enum class ProfileCardSize {
    NORMAL, BIG
}