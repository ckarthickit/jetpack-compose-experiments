package com.kartdroid.composecodelabs.statecodelab.todo

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.R.string
import com.kartdroid.composecodelabs.statecodelab.todo.compoenents.TodoItemInput
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon.Call
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon.Done
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon.Square
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoItem
import com.kartdroid.composecodelabs.statecodelab.todo.model.generateRandomTodoItem
import com.kartdroid.composecodelabs.statecodelab.todo.videmodel.TodoViewModel
import com.kartdroid.composecodelabs.theme.LightColorPalette
import com.kartdroid.composecodelabs.theme.CodelabShapes
import com.kartdroid.composecodelabs.theme.Typography

class TodoActivity : AppCompatActivity() {
    private val todoViewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen(
                todoViewModel.items,
                todoViewModel::addItem
            )
        }
    }
}

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (item: TodoItem) -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) LightColorPalette else LightColorPalette,
        shapes = CodelabShapes,
        typography = Typography
    ) {
        Surface {
            TodoScreenContent(
                items = items,
                onAddItem = onAddItem,
            )
        }
    }
}

@Composable
fun TodoScreenContent(
    items: List<TodoItem>,
    onAddItem: (item: TodoItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TodoItemInput(
            onAddItem,
            Modifier.weight(15.0f)
        )
        LazyColumn(
            modifier = Modifier
                .weight(80.0f)
            //.wrapContentHeight()
        ) {
            this.items(items) { item: TodoItem ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    TaskRow(item = item)
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            onClick = { onAddItem(generateRandomTodoItem()) }
        ) {
            Text(text = stringResource(string.generate_random_title))
        }
    }
}

@Composable
fun TaskRow(item: TodoItem) {
    Text(text = item.task)
    Icon(
        imageVector = item.iconWithDesc.icon,
        contentDescription = stringResource(id = item.iconWithDesc.contentDescription)
    )
}

@Preview(uiMode = UI_MODE_NIGHT_MASK)
@Composable
fun TodoPreview() {
    TodoScreen(
        listOf(
            TodoItem("Task1", Call),
            TodoItem("Task2", Done),
            TodoItem("Task1", Square),
        )
    ) {}
}
