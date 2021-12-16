package com.example.valeeeu.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.valeeeu.R

sealed class Tab(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Home : Tab(route = "home-tab", label = R.string.home, icon = Icons.Rounded.Home)
    object Search :
        Tab(route = "search-tab", label = R.string.search, icon = Icons.Rounded.Search)

    object Messages :
        Tab(route = "messages-tab", label = R.string.messages, icon = Icons.Rounded.Chat)

    object Profile :
        Tab(route = "profile-tab", label = R.string.profile, icon = Icons.Rounded.Person)
}