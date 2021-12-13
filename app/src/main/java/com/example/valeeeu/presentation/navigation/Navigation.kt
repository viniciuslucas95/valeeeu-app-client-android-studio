package com.example.valeeeu.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valeeeu.presentation.screens.HomeScreen
import com.example.valeeeu.presentation.screens.MessagesScreen
import com.example.valeeeu.presentation.screens.ProfileScreen
import com.example.valeeeu.presentation.screens.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        composable(route = "search") {
            SearchScreen(navController = navController)
        }

        composable(route = "messages") {
            MessagesScreen(navController = navController)
        }

        composable(route = "profile") {
            ProfileScreen(navController = navController)
        }
    }
}