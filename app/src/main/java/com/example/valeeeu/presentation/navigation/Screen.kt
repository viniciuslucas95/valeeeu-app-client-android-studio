package com.example.valeeeu.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.valeeeu.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Home : Screen(route = "home", label = R.string.home, icon = Icons.Rounded.Home)
    object Search :
        Screen(route = "search", label = R.string.search, icon = Icons.Rounded.Search)

    object Messages :
        Screen(route = "messages", label = R.string.messages, icon = Icons.Rounded.Chat)

    object Profile :
        Screen(route = "profile", label = R.string.profile, icon = Icons.Rounded.Person)
}