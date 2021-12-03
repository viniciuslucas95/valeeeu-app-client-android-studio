package com.example.valeeeu.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.valeeeu.R
import com.example.valeeeu.models.SummaryProfile
import com.example.valeeeu.ui.theme.Black
import com.example.valeeeu.ui.theme.Blue500
import com.example.valeeeu.ui.theme.Gray300
import com.example.valeeeu.ui.theme.White

@Composable
fun HomeCardSection(
    tag: String?,
    profiles: List<SummaryProfile?>,
    onAddCards: () -> Unit,
    onSeeMore: () -> Unit
) {
    Column {
        Row {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = 0.dp,
                backgroundColor = if (tag == null) Gray300 else White,
                modifier = Modifier
                    .paddingFromBaseline(bottom = 16.dp)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = tag ?: "",
                    style = MaterialTheme.typography.subtitle1,
                    color = Black,
                    modifier = Modifier.width(192.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.more_button),
                style = MaterialTheme.typography.button,
                color = if (tag != null) Blue500 else Gray300,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onSeeMore() }
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(profiles) { _, profile ->
                if (profile != null) {
                    HomeCard(
                        HomeCardSize.BIG,
                        CardData(
                            profile, {}, {})
                    )
                } else {
                    HomeCard(
                        HomeCardSize.BIG,
                        null
                    )
                }
            }
        }
    }
}