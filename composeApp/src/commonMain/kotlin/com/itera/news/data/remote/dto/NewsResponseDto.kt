package com.itera.news.data.remote.dto

import com.itera.news.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<ArticleDto>
)

@Serializable
data class ArticleDto(
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("urlToImage") val urlToImage: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("source") val source: SourceDto? = null
) {
    fun toDomain(): Article {
        return Article(
            title = title ?: "",
            description = description ?: "",
            url = url ?: "",
            imageUrl = urlToImage ?: "",
            publishedAt = publishedAt ?: "",
            sourceName = source?.name ?: ""
        )
    }
}

@Serializable
data class SourceDto(
    @SerialName("name") val name: String? = null
)