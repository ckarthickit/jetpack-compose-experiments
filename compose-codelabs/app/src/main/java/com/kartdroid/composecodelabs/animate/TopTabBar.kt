package com.kartdroid.composecodelabs.animate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string
import com.kartdroid.composecodelabs.animate.TabPage.Home
import com.kartdroid.composecodelabs.animate.TabPage.Work

@Composable
fun AnimateHomeTopBar(
    tabPage: TabPage,
    onTabSelected: (TabPage) -> Unit,
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabPage.ordinal])
            )
        }
    ) {
        TabItem(
            icon = Icons.Default.Home,
            label = stringResource(string.tab_home),
            onClick = { onTabSelected(Home) }
        )
        TabItem(
            icon = Icons.Default.AccountBox,
            label = stringResource(string.tab_work),
            onClick = { onTabSelected(Work) }
        )
    }
}

@Preview("AnimateHomeTopBar - Preview")
@Composable
fun PreviewAnimateHomeTopBar() {
    AnimateHomeTopBar(tabPage = Home, onTabSelected = {})
}

@Composable
fun TabItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = label
        )
    }
}

@Preview("TabItem - Preview")
@Composable
fun PreviewTabItem() {
    Surface {
        TabItem(
            icon = Icons.Default.Home,
            label = "Home",
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun TabIndicatorDemo(
    tabPositions: List<TabPosition>,
    tabPage: TabPage,
) {
    val color = MaterialTheme.colors.onPrimary
    val currentTabPosition = tabPositions[tabPage.ordinal]
    OutlineBox(
        offset = currentTabPosition.left,
        width = currentTabPosition.right - currentTabPosition.left,
        color = color
    )
}

@Preview("Tab Indicator Demo")
@Composable
fun PreviewTabIndicatorDemo() {
    Surface {
        TabRow(
            selectedTabIndex = 0,
            indicator = {
                TabIndicatorDemo(tabPositions = it, tabPage = Home)
            },
        ) {
            TabPage.values().forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }
}

//TODO : Understand how this OutlinedBox works based on the Modifier methods sequence
@Composable
fun OutlineBox(
    offset: Dp,
    width: Dp,
    color: Color,
) {
    Box(
        modifier = Modifier
            // fill it's parent
            .fillMaxSize()
            // wrap it's content
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = offset)
            .width(width)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(4.dp)
            )
    ) /*{
        Text(text = "Text1")
    }*/
}

@Preview("OutlinedBox - Preview")
@Composable
fun PreviewOutlinedBox() {
    Surface(
        modifier = Modifier.height(50.dp)
    ) {
        OutlineBox(
            offset = 0.dp,
            width = 150.dp,
            color = Color.Red
        )
    }
}