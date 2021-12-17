package com.example.valeeeu.presentation.components.profileCards

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.presentation.ui.theme.Yellow

@Composable
fun ProfileCardCaption(
    modifier: Modifier = Modifier,
    averageRating: Float,
    distance: Float,
    lowestPrice: Float? = null,
    singleRatingStar: Boolean = false
) {
    Row(modifier = Modifier.then(modifier)) {
        StarIcon(
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            icon = Icons.Rounded.Star
        )

        if (!singleRatingStar) {
            for (i in 2..5) {
                StarIcon(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    icon = getRatingIconId(averageRating, i.toFloat())
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatRatingText(averageRating),
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

        if (lowestPrice != null) {
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Rounded.Circle,
                contentDescription = null,
                modifier = Modifier
                    .size(2.dp)
                    .align(Alignment.CenterVertically)
                    .alpha(ContentAlpha.disabled)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = formatPriceText(
                    price = lowestPrice,
                    context = LocalContext.current,
                    summarized = true
                ),
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .alpha(ContentAlpha.disabled)
                    .alignBy(LastBaseline)
            )
        }
    }
}

@Composable
private fun StarIcon(modifier: Modifier = Modifier, icon: ImageVector) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Yellow,
        modifier = Modifier
            .size(12.dp)
            .then(modifier)
    )
}

fun formatPriceText(price: Float, context: Context, summarized: Boolean = false): String {
    if (price <= 0) return context.getString(R.string.budget)

    val formattedPrice = "%.2f".format(price).replace(".", ",")

    if (summarized) {
        return "R$ $formattedPrice+"
    }

    val fromText = context.getString(R.string.from_price)

    return "$fromText R$ $formattedPrice"
}

private fun getRatingIconId(rating: Float, range: Float): ImageVector {
    return when (rating) {
        in range..Float.MAX_VALUE -> Icons.Rounded.Star
        in range - 0.5f..range -> Icons.Rounded.StarHalf
        else -> Icons.Rounded.StarBorder
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