package com.example.valeeeu.presentation.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.valeeeu.R
import com.example.valeeeu.data.models.Profile

@Composable
fun ProfileScreen(
    navController: NavController,
    profileType: ProfileType = ProfileType.OTHER,
) {
    ProfileScreenContent(
        goBack = navController::popBackStack,
    )
}

@Composable
private fun ProfileScreenContent(
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Test",
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

enum class ProfileType {
    YOURS, OTHER
}