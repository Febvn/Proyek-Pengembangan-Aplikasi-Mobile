package com.itera.news.core.di

import com.itera.news.core.network.createHttpClient
import com.itera.news.data.remote.api.NewsApi
import com.itera.news.data.repository.NewsRepositoryImpl
import com.itera.news.domain.repository.NewsRepository
import com.itera.news.domain.usecase.GetMbgNewsUseCase
import org.koin.dsl.module

const val NEWS_API_KEY = "YOUR_NEWSAPI_KEY_HERE"

val sharedModule = module {
    single { createHttpClient() }
    single { NewsApi(get(), NEWS_API_KEY) }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    single { GetMbgNewsUseCase(get()) }
}