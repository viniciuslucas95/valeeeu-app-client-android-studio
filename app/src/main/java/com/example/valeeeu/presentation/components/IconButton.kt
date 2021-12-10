package com.example.valeeeu.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    drawableId: Int,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    disabledBackgroundColor: Color = Color.Transparent,
    color: Color = MaterialTheme.colors.surface,
    disabledColor: Color = MaterialTheme.colors.surface,
    isEnabled: Boolean = true,
    contentPadding: Dp = 12.dp,
    contentDescription: String? = null,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val disabledColorWithAlpha =
        Color(
            red = disabledColor.red,
            green = disabledColor.green,
            blue = disabledColor.blue,
            alpha = ContentAlpha.disabled
        )

    Button(
        contentPadding = PaddingValues(contentPadding),
        onClick = { onClick() },
        enabled = isEnabled,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            contentColor = color,
            disabledContentColor = disabledColorWithAlpha
        ),
        shape = RoundedCornerShape(100),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(
                id = drawableId
            ),
            contentDescription = contentDescription
        )
    }
}

