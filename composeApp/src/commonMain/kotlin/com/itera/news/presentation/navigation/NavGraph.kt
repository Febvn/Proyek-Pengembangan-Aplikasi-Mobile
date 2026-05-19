package com.itera.news.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.itera.news.presentation.screen.DetailScreen
import com.itera.news.presentation.screen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // Mulai langsung dari Home
    ) {
        composable(Screen.Splash.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Splash Screen") }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { url ->
                    navController.navigate(Screen.Detail.createRoute(url))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleUrl = backStackEntry.arguments?.getString("articleUrl") ?: ""
            DetailScreen(
                encodedUrl = articleUrl,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Bookmark.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Bookmark Screen") }
        }
        composable(Screen.About.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("About Screen") }
        }
    }
}