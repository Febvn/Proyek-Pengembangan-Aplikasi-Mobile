package com.itera.news.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.itera.news.data.local.NewsDatabase
import com.itera.news.data.remote.api.NewsApi
import com.itera.news.domain.model.Article
import com.itera.news.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val db: NewsDatabase
) : NewsRepository {
    
    private val queries = db.articleQueries

    override suspend fun getMbgNews(query: String?): Result<List<Article>> {
        return try {
            val response = newsApi.getMbgNews(query)
            val articles = response.articles.map { it.toDomain() }
            Result.success(articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveArticle(article: Article) {
        queries.insertArticle(
            url = article.url,
            title = article.title,
            description = article.description,
            imageUrl = article.imageUrl,
            publishedAt = article.publishedAt,
            sourceName = article.sourceName,
            category = article.category
        )
    }

    override suspend fun deleteArticle(article: Article) {
        queries.deleteArticle(article.url)
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return queries.getAllBookmarkedArticles()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { entities ->
                entities.map { entity ->
                    Article(
                        title = entity.title,
                        description = entity.description,
                        url = entity.url,
                        imageUrl = entity.imageUrl,
                        publishedAt = entity.publishedAt,
                        sourceName = entity.sourceName,
                        category = entity.category
                    )
                }
            }
    }

    override fun isArticleBookmarked(url: String): Flow<Boolean> {
        return queries.isArticleBookmarked(url)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .map { it > 0 }
    }
}