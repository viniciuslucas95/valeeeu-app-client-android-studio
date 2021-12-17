package com.example.valeeeu.presentation.components.profileCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.presentation.components.FavoriteButton

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
                isFavorite = profile.isFavorite,
                modifier = Modifier.align(alignment = Alignment.TopEnd)
            )

            Row {
                ProfileCardCompactPicture()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ProfileCardJob(
                        job = profile.job,
                        modifier = Modifier.paddingFromBaseline(20.dp)
                    )

                    ProfileCardName(
                        name = profile.name,
                        modifier = Modifier.paddingFromBaseline(25.dp)
                    )

                    ProfileCardCaption(
                        averageRating = profile.averageRating,
                        distance = profile.distance,
                        lowestPrice = profile.lowestPrice,
                        singleRatingStar = true
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileCardCompactPicture() {
    val fontScale = LocalConfiguration.current.fontScale - 1
    val adjust = if (fontScale > 0) fontScale * 0.5f + 1 else 1f

    Image(
        // Change to get picture from string
        painter = painterResource(id = R.drawable.barber_shop),
        contentDescription = null,
        modifier = Modifier
            .width(width = PROFILE_CARD_COMPACT_HEIGHT.dp * adjust)
            .height(height = PROFILE_CARD_COMPACT_HEIGHT.dp * adjust),
        contentScale = ContentScale.Crop
    )
}