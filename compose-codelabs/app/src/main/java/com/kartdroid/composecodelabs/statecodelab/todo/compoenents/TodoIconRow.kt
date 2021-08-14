package com.kartdroid.composecodelabs.statecodelab.todo.compoenents

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon.Done

@Composable
fun TodoIconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        for (todoIcon in TodoIcon.values()) {
            SelectableIcon(
                icon = todoIcon.icon,
                iconContentDescription = todoIcon.contentDescription,
                onSelected = { onIconChange(todoIcon) },
                isSelected = icon == todoIcon
            )
        }
    }
}

@Composable
fun SelectableIcon(
    icon: ImageVector,
    @StringRes iconContentDescription: Int,
    onSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = onSelected,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = iconContentDescription),
                tint = tintColor
            )
            if (isSelected) {
                Box(
                    Modifier
                        .padding(3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tintColor)
                )
            } else {
                Spacer(modifier = Modifier.padding(3.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectableIcon() {
    Surface {
        SelectableIcon(
            icon = Icons.Default.Star,
            iconContentDescription = R.string.app_name,
            {},
            true
        )
    }
}

@Preview
@Composable
fun PreviewIconRow() {
    Surface {
        TodoIconRow(
            icon = Done,
            onIconChange = {}
        )
    }
}