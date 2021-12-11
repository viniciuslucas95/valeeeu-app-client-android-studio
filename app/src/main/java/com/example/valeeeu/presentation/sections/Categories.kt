package com.example.valeeeu.presentation.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.presentation.components.Category

@Composable
fun Categories() {
    CategoriesContent()
}

@Composable
private fun CategoriesContent() {
    Column {
        Text(
            text = stringResource(R.string.categories),
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .paddingFromBaseline(bottom = 12.dp)
                .padding(horizontal = 16.dp)
                .alpha(ContentAlpha.high)
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            item {
                Category(
                    icon = Icons.Rounded.Devices,
                    onClick = { },
                    contentDescription = stringResource(R.string.technology),
                    firstLine = stringResource(R.string.technology)
                )
            }

            item {
                Spacer(modifier = Modifier.width(width = 16.dp))
            }

            item {
                Category(
                    icon = Icons.Rounded.DirectionsCar,
                    onClick = { },
                    contentDescription = stringResource(R.string.vehicles),
                    firstLine = stringResource(R.string.vehicles)
                )
            }

            item {
                Spacer(modifier = Modifier.width(width = 16.dp))
            }

            item {
                val firstLine = stringResource(R.string.works_and_renovations_first_line)
                val secondLine = stringResource(R.string.works_and_renovations_second_line)

                Category(
                    icon = Icons.Rounded.Handyman,
                    onClick = { },
                    contentDescription = "$firstLine $secondLine",
                    firstLine = firstLine,
                    secondLine = secondLine
                )
            }

            item {
                Spacer(modifier = Modifier.width(width = 16.dp))
            }

            item {
                val firstLine = stringResource(R.string.beauty_and_fashion_first_line)
                val secondLine = stringResource(R.string.beauty_and_fashion_second_line)

                Category(
                    icon = Icons.Rounded.FaceRetouchingNatural,
                    onClick = { },
                    contentDescription = "$firstLine $secondLine",
                    firstLine = firstLine,
                    secondLine = secondLine
                )
            }

            item {
                Spacer(modifier = Modifier.width(width = 16.dp))
            }

            item {
                Category(
                    icon = Icons.Rounded.ShoppingBag,
                    onClick = { },
                    contentDescription = stringResource(R.string.sales),
                    firstLine = stringResource(R.string.sales)
                )
            }
        }
    }
}
