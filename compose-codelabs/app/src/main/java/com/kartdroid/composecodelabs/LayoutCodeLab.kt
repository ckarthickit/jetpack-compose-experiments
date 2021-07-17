package com.kartdroid.composecodelabs

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.TabNames.HOME
import com.kartdroid.composecodelabs.TabNames.LIST1
import com.kartdroid.composecodelabs.TabNames.LIST2
import com.kartdroid.composecodelabs.ui.theme.ComposeCodeLabsTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

enum class TabNames {
    HOME,
    LIST1,
    LIST2
}

class LayoutCodeLab : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutComposeDemo()
        }
    }
}

@Composable
fun LayoutComposeDemo() {
    ComposeCodeLabsTheme {
        var currentTab: TabNames by remember { mutableStateOf(HOME) }
        val columnScrollState = rememberScrollState()
        val lazyColumnScrollState = rememberLazyListState()
        //val composeLocal = compositionLocalOf { currentTab }

        Scaffold(
            topBar = {
                TopBarContent()
            },
            bottomBar = {
                BottomBarContent(
                    tabChanger = {
                        currentTab = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { padding ->
            Log.d("KC_DEBUG", currentTab.name)
            when (currentTab) {
                HOME -> {
                    BodyContentHome(Modifier.padding(padding))
                }
                LIST1 -> {
                    BodyContentSimpleList(Modifier.padding(padding), columnScrollState)
                }
                LIST2 -> {
                    BodyContentLazyList(Modifier.padding(padding), lazyColumnScrollState)
                }
            }
        }

        /*CompositionLocalProvider(composeLocal provides currentTab) {

        }*/
    }
}

/************* PHOTOGRAPHER CARD ************/

@Composable
fun PhotographerCard() {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { }
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically) //Available only in RowScope
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }
            //Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
        }
    }
}

/*************** SCAFFOLD DEMO **************/

@Composable
fun TopBarContent(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Scaffold Example",
                modifier = Modifier
                    .padding(4.dp)
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = null)
            }
        }
    ) /*{
        Text(
            text = "Scaffold Example",
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )
    }*/
}

@Composable
fun BodyContentHome(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        PhotographerCard()
        Divider(color = MaterialTheme.colors.secondaryVariant)
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Sample"
        )
    }
}

@Composable
fun BottomBarContent(
    tabChanger: (tabName: TabNames) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(modifier = modifier) {
        IconButton(onClick = { tabChanger.invoke(HOME) }, modifier = Modifier.weight(1.0f)) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        }
        IconButton(onClick = { tabChanger.invoke(LIST1) }, modifier = Modifier.weight(1.0f)) {
            Icon(imageVector = Icons.Filled.List, contentDescription = null)
        }
        IconButton(onClick = { tabChanger.invoke(LIST2) }, modifier = Modifier.weight(1.0f)) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = null)
        }
    }
}


/********************* SIMPLE LIST ******************/


@Composable
fun BodyContentSimpleList(modifier: Modifier, scrollState: ScrollState) {
    Column(modifier = modifier.verticalScroll(scrollState)) {
        repeat(50) {
            Text(text = "Column Item #$it", modifier = Modifier.padding(8.dp))
            Divider(color= MaterialTheme.colors.onSecondary)
        }
    }
}


/**************** LAZY LIST *****************/

@Composable
fun BodyContentLazyList(modifier: Modifier, lazyListState: LazyListState) {
    val listSize = 100
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll To Top")
            }
            Button(onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text(text = "Scroll To Bottom")
            }
        }
        LazyColumn(modifier = modifier, state = lazyListState) {
            items(List(listSize) { it }) { item ->
                ImageListItem(itemIndex = item)
                Divider(color = MaterialTheme.colors.onSurface)
            }
        }
    }
}

@Composable
fun ImageListItem(itemIndex: Int) {
    Row {
        CoilImage(
            data = "https://developer.android.com/images/brand/Android_Robot.png",
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Lazy Item #$itemIndex", modifier = Modifier.padding(8.dp))
    }
}

/***************** PREVIEW *****************/

@Preview(showBackground = true)
@Composable
fun Preview() {
    LayoutComposeDemo()
}


