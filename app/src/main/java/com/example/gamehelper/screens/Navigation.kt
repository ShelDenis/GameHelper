package com.example.gamehelper

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(nd: NecessaryData) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "initial_screen") {
        composable("initial_screen") { InitialScreen(navController) }
        composable("true_or_true_screen") { TrueOrTrue(navController, nd) }
        composable("true_settings_screen") {TrueSettings(navController, nd)}
        composable("true_congrat_screen") {CongratulationScreen(navController, nd)}
    }
}