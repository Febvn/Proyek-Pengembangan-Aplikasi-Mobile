package com.itera.news.core.di

import com.itera.news.core.network.createHttpClient
import com.itera.news.data.remote.api.NewsApi
import com.itera.news.data.repository.NewsRepositoryImpl
import com.itera.news.domain.repository.NewsRepository
import com.itera.news.domain.usecase.GetMbgNewsUseCase
import com.itera.news.presentation.viewmodel.NewsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    // Network
    single { createHttpClient() }
    
    // API (Dummy API key for now, replace with BuildKonfig later)
    single { NewsApi(get(), "YOUR_API_KEY_HERE") } 
    
    // Repository & UseCase
    singleOf(::NewsRepositoryImpl) bind NewsRepository::class
    singleOf(::GetMbgNewsUseCase)
    
    // ViewModel
    viewModelOf(::NewsViewModel)
}

expect val platformModule: Module