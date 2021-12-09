package com.example.valeeeu.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    color: Color = MaterialTheme.colors.onSurface,
    disabledColor: Color = MaterialTheme.colors.onSurface,
    isEnabled: Boolean = true,
    contentPadding: Dp = 24.dp
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
        onClick = { onClick() }, elevation = null,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
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
            contentDescription = null
        )
    }
}

