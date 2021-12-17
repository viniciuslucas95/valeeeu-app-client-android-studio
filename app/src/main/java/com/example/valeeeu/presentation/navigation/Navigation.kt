package com.example.valeeeu.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valeeeu.presentation.screens.*
import com.example.valeeeu.presentation.screens.profileScreens.OtherProfileScreen
import com.example.valeeeu.presentation.screens.profileScreens.YourProfileScreen

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
            YourProfileScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(startDestination = "home-screen", route = Tab.Home.route) {
        composable(route = "home-screen") {
            HomeScreen(navController = navController)
        }

//        composable(
//            route = "profile-screen?username={username}&name={name}&job={job}&description={description}&averageRating={averageRating}&distance={distance}&isFavorite={isFavorite}",
//            arguments = listOf(
//                navArgument(name = "username") { },
//                navArgument(name = "name") { },
//                navArgument(name = "job") { },
//                navArgument(name = "description") { nullable = true },
//                navArgument(name = "averageRating") { type = NavType.FloatType },
//                navArgument(name = "distance") { type = NavType.FloatType },
//                navArgument(name = "isFavorite") { type = NavType.BoolType },
//            )
//        ) {
//            val username = it.arguments?.getString("username")!!
//            val name = it.arguments?.getString("name")!!
//            val job = it.arguments?.getString("job")!!
//            val description = it.arguments?.getString("description")
//            val averageRating = it.arguments?.getFloat("averageRating")!!
//            val distance = it.arguments?.getFloat("distance")!!
//            val isFavorite = it.arguments?.getBoolean("isFavorite")!!
//
//            OtherProfileScreen(
//                navController = navController,
//                username = username,
//                name = name,
//                job = job,
//                description = description,
//                averageRating = averageRating,
//                distance = distance,
//                isFavorite = isFavorite
//            )
//        }

        otherProfileScreen(navController = navController)
    }
}

fun NavGraphBuilder.searchGraph(navController: NavController) {
    navigation(startDestination = "search-screen", route = Tab.Search.route) {
        composable(route = "search-screen") {
            SearchScreen(navController = navController)
        }

//        composable(
//            route = "profile-screen?username={username}",
//            arguments = listOf(navArgument(name = "username") { nullable = true })
//        ) {
//            OtherProfileScreen(navController = navController)
//        }

        otherProfileScreen(navController = navController)
    }
}

private fun NavGraphBuilder.otherProfileScreen(navController: NavController) {
    composable(
        route = "profile-screen?username={username}&name={name}&job={job}&description={description}&averageRating={averageRating}&distance={distance}&isFavorite={isFavorite}&picture={picture}",
        arguments = listOf(
            navArgument(name = "username") { },
            navArgument(name = "name") { },
            navArgument(name = "job") { },
            navArgument(name = "description") { nullable = true },
            navArgument(name = "averageRating") { type = NavType.FloatType },
            navArgument(name = "distance") { type = NavType.FloatType },
            navArgument(name = "isFavorite") { type = NavType.BoolType },
            navArgument(name = "picture") { nullable = true }
        )
    ) {
        val username = it.arguments?.getString("username")!!
        val name = it.arguments?.getString("name")!!
        val job = it.arguments?.getString("job")!!
        val description = it.arguments?.getString("description")
        val averageRating = it.arguments?.getFloat("averageRating")!!
        val distance = it.arguments?.getFloat("distance")!!
        val isFavorite = it.arguments?.getBoolean("isFavorite")!!
        val picture = it.arguments?.getString("picture")!!

        OtherProfileScreen(
            navController = navController,
            username = username,
            name = name,
            job = job,
            description = description,
            averageRating = averageRating,
            distance = distance,
            isFavorite = isFavorite,
            picture = picture
        )
    }
}