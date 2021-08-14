package com.kartdroid.composecodelabs.statecodelab.todo.compoenents

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoIcon
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoItem

@Composable
fun TodoItemInput(
    onAddItem: (item: TodoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val (todoTask, setTodoTask) = remember { mutableStateOf("") }
    val (todoIcon, setTodoIcon) = remember { mutableStateOf(TodoIcon.Done) }
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            TodoInputTextField(
                todoTask,
                setTodoTask,
                Modifier
                    .weight(1f)
            )
            TodoInputButton(
                onClick = {
                    if (todoTask.isNotBlank()) {
                        onAddItem(
                            TodoItem(
                                todoTask,
                                todoIcon
                            )
                        )
                    } else {
                        Toast.makeText(context, "Please Enter some Task", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        //println("todoTask: $todoTask")
        if (todoTask.isNotBlank()) {
            TodoIconRow(icon = todoIcon, onIconChange = setTodoIcon)
        }
    }
}

@Composable
fun TodoInputTextField(
    text: String,
    setText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * Hoisting this so that `TodoItemInput` widget can access
     */
    //val (text, setText) = remember { mutableStateOf("") }
    TodoInputText(
        text,
        setText,
        modifier
    )
}

@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
    )
}

@Composable
fun TodoInputButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        shape = CircleShape
    ) {
        Text(text = "Add")
    }
}

@Preview
@Composable
fun PreviewTodoItemInput() {
    Surface {
        TodoItemInput({})
    }
}
