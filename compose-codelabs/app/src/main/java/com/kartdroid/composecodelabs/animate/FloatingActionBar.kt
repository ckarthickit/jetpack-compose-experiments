package com.kartdroid.composecodelabs.animate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateHomeFAB(
    expanded: Boolean,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
            )
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = stringResource(string.edit),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewAnimateHomeFAB() {
    Surface {
        AnimateHomeFAB(expanded = true)
    }
}