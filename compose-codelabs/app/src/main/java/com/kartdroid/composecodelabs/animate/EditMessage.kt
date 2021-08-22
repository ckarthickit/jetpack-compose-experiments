package com.kartdroid.composecodelabs.animate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string

/**
 * AnimatedVisibility
 *   -> If visibility changes, execute and animation and then change the visibility
 *   -> Enter animation if toggled from false to true
 *   -> Exit animation if toggled from true to false
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditMessage(isShown: Boolean) {
    AnimatedVisibility(
        visible = isShown,
        enter = slideInVertically(
            // Enters by sliding down from offset -fullHeight to 0.
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding up from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            elevation = 4.dp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(string.edit_message),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview("Edit Message Preview")
@Composable
fun PreviewEditMessage() {
    EditMessage(isShown = true)
}