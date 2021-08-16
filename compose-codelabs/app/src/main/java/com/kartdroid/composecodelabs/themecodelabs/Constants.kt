package com.kartdroid.composecodelabs.themecodelabs

import com.kartdroid.composecodelabs.R.drawable
import com.kartdroid.composecodelabs.themecodelabs.model.Article
import com.kartdroid.composecodelabs.themecodelabs.model.ArticleMetadata
import java.util.UUID

val FEATURED_ARTICLE = Article(
    title = "A Sample Title for the Article - (This is an H6 styled Title)",
    description = "Article Body",
    bannerIconRes = drawable.post,
    thumbNailIconRes = drawable.post_thumb,
    metadata = ArticleMetadata(
        "Aug 8th",
        15,
        listOf("Tagged", "Theme", "Material"),
    ),
)

val OTHER_ARTICLES = arrayOf(
    FEATURED_ARTICLE.copy(id = UUID.randomUUID()),
    FEATURED_ARTICLE.copy(title = "Another Sample Article", id = UUID.randomUUID()),
    FEATURED_ARTICLE.copy(title = "Article Item Number #3", id = UUID.randomUUID()),
)