package com.kartdroid.composecodelabs.themecodelabs.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class Article(
    val title: String,
    val description: String,
    @DrawableRes val bannerIconRes: Int,
    @DrawableRes val thumbNailIconRes: Int,
    val metadata: ArticleMetadata,
    val id : UUID = UUID.randomUUID()
)

data class ArticleMetadata(
    val publishDate: String,
    val totalReadTime: Int,
    val tags: List<String>,
)
