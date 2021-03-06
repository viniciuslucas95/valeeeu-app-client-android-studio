package com.example.valeeeu.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Category(
    icon: ImageVector,
    onClick: () -> Unit,
    firstLine: String,
    secondLine: String = "",
    contentDescription: String?
) {
    val interactionSource = remember { MutableInteractionSource() }

    CategoryContent(
        icon = icon,
        onClick = onClick,
        firstLine = firstLine,
        secondLine = secondLine,
        contentDescription = contentDescription,
        interactionSource = interactionSource
    )
}


@Composable
private fun CategoryContent(
    icon: ImageVector,
    onClick: () -> Unit,
    firstLine: String,
    secondLine: String = "",
    contentDescription: String?,
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
            Button(
                onClick = { onClick() },
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    disabledBackgroundColor = MaterialTheme.colors.surface,
                    contentColor = MaterialTheme.colors.primary,
                    disabledContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                ),
                shape = RoundedCornerShape(100),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
            }

            Text(
                text = firstLine,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                modifier = Modifier.paddingFromBaseline(top = 24.dp)
            )

            Text(
                text = secondLine,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                modifier = Modifier.paddingFromBaseline(bottom = 8.dp)
            )
        }
    }
}