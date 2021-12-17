package com.example.valeeeu.presentation.components.profileCards

import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ProfileCardJob(modifier: Modifier = Modifier, job: String) {
    Text(
        text = job,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.overline,
        modifier = Modifier
            .alpha(ContentAlpha.high)
            .then(modifier)
    )
}

@Composable
fun ProfileCardName(modifier: Modifier = Modifier, name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h6,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .alpha(ContentAlpha.high)
            .then(modifier)
    )
}

@Composable
fun ProfileCardDescription(
    modifier: Modifier = Modifier,
    description: String,
    maxLines: Int = 3,
    style: TextStyle = MaterialTheme.typography.body1
) {
    Text(
        text = description,
        style = style,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .alpha(alpha = ContentAlpha.medium)
            .then(modifier)
    )
}