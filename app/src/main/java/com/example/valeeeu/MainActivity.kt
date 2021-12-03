package com.example.valeeeu

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valeeeu.repositories.FakeSummaryProfileRepository
import com.example.valeeeu.repositories.FakeTagRepository
import com.example.valeeeu.screens.HomeScreen
import com.example.valeeeu.ui.theme.ValeeeuTheme
import com.example.valeeeu.viewModels.HomeViewModel

class MainActivity : ComponentActivity() {
    private val summaryProfileRepository = FakeSummaryProfileRepository()
    private val tagRepository = FakeTagRepository()
    private val homeViewModel = HomeViewModel(summaryProfileRepository, tagRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValeeeuTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                homeViewModel.sections.value,
                                homeViewModel::addSection,
                                homeViewModel::addCard,
                                homeViewModel::seeMore
                            )
                        }
                    }
                }
            }
        }
    }
}