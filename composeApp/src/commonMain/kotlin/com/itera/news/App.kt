package com.itera.news

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.itera.news.presentation.navigation.NavGraph
import org.koin.compose.KoinContext

@Composable
fun App() {
    MaterialTheme {
        // Sementara platformModule dikomentari
        // KoinContext(modules = listOf(sharedModule, platformModule)) {
            NavGraph()
        // }
    }
}