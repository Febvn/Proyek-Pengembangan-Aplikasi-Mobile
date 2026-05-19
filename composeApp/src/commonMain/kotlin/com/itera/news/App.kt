package com.itera.news

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.itera.news.core.di.platformModule
import com.itera.news.core.di.sharedModule
import com.itera.news.presentation.navigation.NavGraph
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(sharedModule, platformModule)
    }) {
        MaterialTheme {
            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}