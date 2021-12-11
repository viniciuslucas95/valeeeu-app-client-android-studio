package com.example.valeeeu.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Category(
    icon: ImageVector,
    onClick: () -> Unit,
    label: String,
    contentDescription: String?,
    maxLines: Int = 1
) {
    val interactionSource = remember { MutableInteractionSource() }

    CategoryContent(
        icon = icon,
        onClick = onClick,
        label = label,
        contentDescription = contentDescription,
        maxLines = maxLines,
        interactionSource = interactionSource
    )
}


@Composable
private fun CategoryContent(
    icon: ImageVector,
    onClick: () -> Unit,
    label: String,
    contentDescription: String?,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource
) {
    Surface(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(width = 72.dp)
        ) {
            IconButton(
                icon = icon,
                onClick = { onClick() },
                interactionSource = interactionSource,
                backgroundColor = MaterialTheme.colors.surface,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                    .padding(top = 4.dp),
                contentDescription = contentDescription
            )

            Text(
                text = label,
                maxLines = maxLines,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .alpha(alpha = ContentAlpha.medium)
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
            )
        }
    }
}