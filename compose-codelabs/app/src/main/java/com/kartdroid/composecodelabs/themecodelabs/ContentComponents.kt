package com.kartdroid.composecodelabs.themecodelabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R
import com.kartdroid.composecodelabs.themecodelabs.model.Article
import com.kartdroid.composecodelabs.themecodelabs.model.ArticleMetadata
import com.kartdroid.composecodelabs.themecodelabs.theme.CodeLabTheme
import java.util.Locale

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
        contentColor = MaterialTheme.colors.primary,
        /**
         * This Node is marked as "Heading" for accessibility
         */
        modifier = modifier.semantics { heading() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun FeaturedArticle(
    article: Article,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
        /**
         * Card Default Shape is `MaterialTheme.shapes.medium`
         */
    ) {
        Column {
            Image(
                painter = painterResource(id = article.bannerIconRes),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            val textPadding = Modifier.padding(8.dp)
            Text(
                text = article.title,
                style = MaterialTheme.typography.h6,
                modifier = textPadding
            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.body1,
                modifier = textPadding
            )
            MetadataText(
                articleMetadata = article.metadata,
                style = MaterialTheme.typography.body2,
                modifier = textPadding
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ArticleItem(
    article: Article,
) {
    ListItem(
        icon = {
            Image(
                painter = painterResource(id = article.thumbNailIconRes),
                contentDescription = null,
                modifier = Modifier.clip(shape = MaterialTheme.shapes.small)
            )
        },
        text = { Text(text = article.title) },
        secondaryText = {
            MetadataText(
                articleMetadata = article.metadata,
                style = MaterialTheme.typography.body2
            )
        },
        modifier = Modifier
            .clickable {/* TODO */ }
            .padding(vertical = 4.dp),
    )
    Divider(
        thickness = 2.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun MetadataText(
    articleMetadata: ArticleMetadata,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    val divider = "  •  "
    val tagDivider = "  "
    val text = buildAnnotatedString {
        append(articleMetadata.publishDate)
        append(divider)

        append(stringResource(id = R.string.read_time, articleMetadata.totalReadTime))
        append(divider)

        val tagStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
            background = MaterialTheme.colors.primary.copy(alpha = 0.1f)
        )
        articleMetadata.tags.forEachIndexed { index, tag ->
            if (index != 0) {
                append(tagDivider)
            }
            withStyle(tagStyle) {
                append(" ")
                append(tag.uppercase(Locale.getDefault()))
                append(" ")
            }
        }
    }

    /**
     *  Text Content Alpha is medium in this sub-tree unless
     *  `Text(color=) is specified`
     */
    CompositionLocalProvider(
        LocalContentAlpha provides ContentAlpha.medium
    ) {
        Text(
            text = text,
            /**
             * Content Color shouldn't be explicitly specified
             * as we have already asked the Text Component
             * to pick it from Local Provider
             */
            /*color = Color.Black,*/
            style = style,
            modifier = modifier
        )
    }
}

@Preview("Header Preview")
@Composable
fun PreviewHeader() {
    CodeLabTheme(false) {
        Surface {
            Header(text = "A Sample Header")
        }
    }
}

@Preview("Featured Article")
@Composable
fun PreviewFeaturedArticle() {
    CodeLabTheme {
        Surface {
            FeaturedArticle(article = FEATURED_ARTICLE)
        }
    }
}

@ExperimentalMaterialApi
@Preview("Article Item")
@Composable
fun PreviewArticleItem() {
    CodeLabTheme {
        Surface {
            ArticleItem(article = FEATURED_ARTICLE)
        }
    }
}

