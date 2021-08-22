package com.kartdroid.composecodelabs.animate

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string

/**
 * Animating ContentSize if Content Size changes
 */
@Composable
fun ContentListItem(
    index: Int,
) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    Surface(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                setExpanded(!expanded)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                /** This `Column` animates its size when its content changes.*/
                .animateContentSize()
        ) {
            Text(
                text = "Item # $index",
                modifier = Modifier.padding(16.dp)
            )
            if (expanded) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
                Text(
                    text = stringResource(id = string.lorem_ipsum),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewContentListItem() {
    Surface {
        ContentListItem(index = 1)
    }
}