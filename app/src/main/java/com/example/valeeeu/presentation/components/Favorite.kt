package com.example.valeeeu.presentation.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.presentation.ui.theme.Red

@Composable
fun FavoriteButton(modifier: Modifier = Modifier, isFavorited: Boolean = false) {
    if (isFavorited) {
        IconButton(
            drawableId = R.drawable.ic_favorite_toggled,
            onClick = { },
            color = Red,
            elevation = null,
            modifier = Modifier
                .padding(top = 1.dp, end = 2.dp)
                .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                .then(modifier)
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
                .padding(top = 1.dp, end = 2.dp)
                .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                .then(modifier)
        )
    }
}