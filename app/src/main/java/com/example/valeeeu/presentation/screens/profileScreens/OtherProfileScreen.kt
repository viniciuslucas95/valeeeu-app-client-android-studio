package com.example.valeeeu.presentation.screens.profileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valeeeu.R
import com.example.valeeeu.presentation.components.profileCards.ProfileCardCaption
import com.example.valeeeu.presentation.components.profileCards.ProfileCardJob
import com.example.valeeeu.presentation.components.profileCards.ProfileCardName
import com.example.valeeeu.presentation.components.profileCards.ProfileCardDescription
import com.example.valeeeu.presentation.navigation.BottomNavBar

private const val PICTURE_HEIGHT = 200
private const val PROFILE_PICTURE_SIZE = 75

@Composable
fun OtherProfileScreen(
    navController: NavController,
    username: String,
    name: String,
    job: String,
    description: String?,
    averageRating: Float,
    distance: Float,
    isFavorite: Boolean,
    picture: String?
) {
    val profilePicture = remember { mutableStateOf<String?>(null) }
    val pictures = remember { mutableStateOf(mutableListOf<String>()) }

    if (picture != null) {
        pictures.value = (pictures.value + picture) as MutableList<String>
    }

    OtherProfileScreenContent(
        goBack = navController::popBackStack,
        username = username,
        name = name,
        job = job,
        description = description,
        averageRating = averageRating,
        distance = distance,
        isFavorite = isFavorite,
        navController = navController,
        pictures = pictures.value,
        profilePicture = profilePicture.value
    )
}

@Composable
private fun OtherProfileScreenContent(
    navController: NavController,
    goBack: () -> Unit,
    username: String,
    name: String,
    job: String,
    description: String?,
    averageRating: Float,
    distance: Float,
    isFavorite: Boolean,
    pictures: List<String>,
    profilePicture: String?
) {
    Scaffold(
        topBar = { TopBar(title = username, goBack = goBack) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.barber_shop),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = PICTURE_HEIGHT.dp),
                contentScale = ContentScale.Crop
            )

            Info(
                profilePicture = profilePicture,
                averageRating = averageRating,
                distance = distance,
                name = name,
                job = job,
                description = description
            )

            Divider(modifier = Modifier.padding(top = 21.dp, start = 16.dp, end = 16.dp))
        }
    }
}

@Composable
private fun TopBar(
    goBack: () -> Unit,
    title: String
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.alpha(alpha = ContentAlpha.high)
                )
            }
        }
    )
}

@Composable
private fun Info(
    profilePicture: String?,
    averageRating: Float,
    distance: Float,
    name: String,
    job: String,
    description: String?,
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.barber_shop),
                contentDescription = null,
                modifier = Modifier
                    .size(size = PROFILE_PICTURE_SIZE.dp)
                    .clip(RoundedCornerShape(100)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp)) {
                ProfileCardJob(job = job, modifier = Modifier.paddingFromBaseline(12.dp))

                ProfileCardName(name = name, modifier = Modifier.paddingFromBaseline(29.dp))

                ProfileCardCaption(
                    modifier = Modifier.paddingFromBaseline(15.dp),
                    averageRating = averageRating,
                    distance = distance
                )
            }
        }

        if (description != null) {
            ProfileCardDescription(
                modifier = Modifier.paddingFromBaseline(top = 32.dp),
                description = description,
                maxLines = 2
            )
        }

        SeeMoreButton()
    }
}


@Composable
private fun SeeMoreButton() {
    Row {
        Spacer(modifier = Modifier.weight(weight = 1f))

        TextButton(
            onClick = { },
            modifier = Modifier.paddingFromBaseline(top = 27.dp)
        ) {
            Text(
                text = stringResource(R.string.see_more),
                style = MaterialTheme.typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.weight(weight = 1f))
    }
}