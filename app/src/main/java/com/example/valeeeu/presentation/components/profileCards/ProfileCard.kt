package com.example.valeeeu.presentation.components.profileCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.presentation.components.FavoriteButton

const val PROFILE_CARD_BIG_HEIGHT = 186
const val PROFILE_CARD_NORMAL_HEIGHT = 154
const val PROFILE_CARD_BIG_WIDTH = 312
const val PROFILE_CARD_NORMAL_WIDTH = 224

@Composable
fun ProfileCard(
    size: ProfileCardSize = ProfileCardSize.NORMAL,
    profile: SummarizedProfile,
    onCardClick: () -> Unit
) {
    ProfileCardContent(
        size = size,
        profile = profile,
        onCardClick = onCardClick
    )
}

@Composable
private fun ProfileCardContent(
    size: ProfileCardSize,
    profile: SummarizedProfile,
    onCardClick: () -> Unit
) {
    val fontScale = LocalConfiguration.current.fontScale - 1
    val adjust = if (fontScale > 0) fontScale * 0.5f + 1 else 1f

    Card(
        modifier = Modifier
            .width(width = getWidth(size) * adjust)
            .clickable { onCardClick() }
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

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            ProfileCardJob(job = profile.job, modifier = Modifier.paddingFromBaseline(28.dp))

            ProfileCardName(name = profile.name, modifier = Modifier.paddingFromBaseline(33.dp))

            ProfileCardCaption(averageRating = profile.averageRating, distance = profile.distance)

            ProfileCardDescription(
                description = profile.description ?: "\n\n",
                style = when (size) {
                    ProfileCardSize.NORMAL -> MaterialTheme.typography.body2
                    else -> MaterialTheme.typography.body1
                },
                modifier = Modifier.paddingFromBaseline(top = 29.dp, bottom = 16.dp)
            )
        }
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

enum class ProfileCardSize {
    NORMAL, BIG
}