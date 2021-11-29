package com.example.valeeeu

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.valeeeu.components.HomeCard
import com.example.valeeeu.components.HomeCardSize
import com.example.valeeeu.models.SummaryProfile
import com.example.valeeeu.ui.theme.ValeeeuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValeeeuTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val profile = SummaryProfile(
                        "Carlos Antônio",
                        "Barbearia",
                        "Cortamos todos os tipos de cabelo, desde os cortes mais modernos até os mais convencionais. Conosco o cliente sempre sai satisfeito, aquele cabelo sempre estiloso e bonito!",
                        3.5f,
                        800f,
                        true
                    )

                    HomeCard(profile, HomeCardSize.BIG,
                        {
                            println("Card pressed...")
                        },
                        {
                            println("Favorite pressed...")
                        })
                }
            }
        }
    }
}