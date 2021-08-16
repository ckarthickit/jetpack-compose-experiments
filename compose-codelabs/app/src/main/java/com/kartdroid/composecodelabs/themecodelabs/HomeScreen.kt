package com.kartdroid.composecodelabs.themecodelabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R
import com.kartdroid.composecodelabs.themecodelabs.theme.CodeLabTheme

@Composable
fun HomeScreen() {
    CodeLabTheme {
        Scaffold(
            topBar = { HomeTopBar() },
            bottomBar = { HomeBottomBar() },
            floatingActionButton = { HomeFAB() }
        ) { paddingValues ->
            HomeContent(contentPadding = paddingValues)
        }
    }
}

@Composable
fun HomeTopBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = stringResource(id = R.string.home),
                modifier = Modifier.padding(8.dp)
            )
        },
        title = { Text(text = "Themes CodeLab") },
        backgroundColor = MaterialTheme.colors.primarySurface
    )

}

@Composable
fun HomeBottomBar() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primarySurface,
        contentColor = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.primarySurface),
    ) {
        val icons = arrayOf(Icons.Default.Home, Icons.Default.Email, Icons.Default.Search, Icons.Default.MoreVert)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (icon in icons) {
                /**
                 * IconButton to wrap Icon and make it clickable
                 */
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeFAB() {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colors.primaryVariant
    ) {
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        item { Header(text = stringResource(id = R.string.top_article)) }
        item {
            FeaturedArticle(
                FEATURED_ARTICLE,
                Modifier.padding(8.dp),
            )
        }

        item { Header(text = stringResource(id = R.string.other_articles)) }
        items(
            items = OTHER_ARTICLES,
            key = { it.id }
        ) { article ->
            ArticleItem(article = article)
        }
    }
}

@Preview("Home Top Bar")
@Composable
fun PreviewHomeTopBar() {
    HomeTopBar()
}

@Preview("Home Bottom Bar")
@Composable
fun PreviewHomeBottomBar() {
    HomeBottomBar()
}

@Preview("Home Floating Action Button")
@Composable
fun PreviewHomeFAB() {
    Surface(
        color = MaterialTheme.colors.surface
    ) {
        HomeFAB()
    }
}

@Preview("Theme Screen Preview")
@Composable
fun PreviewThemeScreen() {
    HomeScreen()
}