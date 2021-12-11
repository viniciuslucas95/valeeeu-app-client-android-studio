package com.example.valeeeu.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valeeeu.presentation.ui.theme.Red

@Composable
fun FavoriteButton(modifier: Modifier = Modifier, isFavorite: Boolean = false) {
    if (isFavorite) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(top = 1.dp, end = 2.dp)
                .then(modifier)
        ) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = Red
            )
        }
    } else {
        val iconColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)

        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(top = 1.dp, end = 2.dp)
                .then(modifier)
        ) {
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}