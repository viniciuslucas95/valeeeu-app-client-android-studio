package com.example.valeeeu.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.valeeeu.presentation.ui.theme.Black
import com.example.valeeeu.presentation.ui.theme.White

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    val disabledColor = Color(
        if (isSystemInDarkTheme()) White.red else Black.red,
        if (isSystemInDarkTheme()) White.green else Black.green,
        if (isSystemInDarkTheme()) White.blue else Black.blue,
        ContentAlpha.disabled
    )

    Button(
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = disabledColor
        ),
        enabled = isEnabled,
        onClick = { onClick() },
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
    }
}