package com.example.valeeeu.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valeeeu.presentation.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Tab.Home.route) {
        homeGraph(navController = navController)

        searchGraph(navController = navController)

        composable(route = Tab.Messages.route) {
            MessagesScreen(navController = navController)
        }

        composable(route = Tab.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(startDestination = "home-screen", route = Tab.Home.route) {
        composable(route = "home-screen") {
            HomeScreen(navController = navController)
        }

        composable(
            route = "profile-screen?username={username}",
            arguments = listOf(navArgument(name = "username") { nullable = true })
        ) {
            ProfileScreen(
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.searchGraph(navController: NavController) {
    navigation(startDestination = "search-screen", route = Tab.Search.route) {
        composable(route = "search-screen") {
            SearchScreen(navController = navController)
        }

        composable(
            route = "profile-screen?username={username}",
            arguments = listOf(navArgument(name = "username") { nullable = true })
        ) {
            ProfileScreen(
                navController = navController
            )
        }
    }
}