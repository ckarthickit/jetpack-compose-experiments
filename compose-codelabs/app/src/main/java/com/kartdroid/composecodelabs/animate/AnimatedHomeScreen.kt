package com.kartdroid.composecodelabs.animate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string
import com.kartdroid.composecodelabs.animate.TabPage.Home
import com.kartdroid.composecodelabs.theme.ComposeCodeLabsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TabPage {
    Home, Work
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateHomeScreen() {

    val (currentTabPage, setCurrentTabPage) = remember { mutableStateOf(Home) }
    val contentListState = rememberLazyListState()

    var editMessageShown by remember { mutableStateOf(false) }
    suspend fun showEditMessage() {
        editMessageShown = true
        delay(2000)
        editMessageShown = false
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AnimateHomeTopBar(
                tabPage = currentTabPage,
                onTabSelected = {
                    setCurrentTabPage(it)
                }
            )
        },
        floatingActionButton = {
            AnimateHomeFAB(
                expanded = contentListState.isScrollingUp(),
                onClick = {
                    coroutineScope.launch {
                        showEditMessage()
                    }
                }
            )
        }
    ) { paddingValues ->
        AnimateHomeContent(
            currentTabPage,
            contentListState,
            paddingValues,
            editMessageShown,
        )
    }
}

/**
 * "animateColorAsState" used to Animate Surface
 * background color
 */
@Composable
private fun AnimateHomeContent(
    currentTabPage: TabPage,
    lazyListState: LazyListState,
    paddingValues: PaddingValues,
    showEditMessage: Boolean = false,
) {

    /** Animating Color As State **/
    val color = if (currentTabPage == Home) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.secondaryVariant
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(1500)
    )
    Surface(
        color = animatedColor,
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {
        LazyColumn(
            state = lazyListState,
        ) {
            items(5) { index ->
                ContentListItem(index = index)
            }
            // Adding spacer to make it scrollable
            items(20) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
        }
        EditMessage(isShown = showEditMessage)
    }

}

@Preview("Animate Demo - Home Screen")
@Composable
fun PreviewAnimateScreen() {
    ComposeCodeLabsTheme {
        AnimateHomeScreen()
    }
}
