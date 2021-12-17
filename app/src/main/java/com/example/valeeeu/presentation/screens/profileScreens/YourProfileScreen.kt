package com.example.valeeeu.presentation.screens.profileScreens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.valeeeu.R

@Composable
fun YourProfileScreen(
    navController: NavController
) {
    YourProfileScreenContent(
        goBack = navController::popBackStack,
    )
}

@Composable
private fun YourProfileScreenContent(
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Your Profile",
                goBack = goBack
            )
        }
    ) {

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