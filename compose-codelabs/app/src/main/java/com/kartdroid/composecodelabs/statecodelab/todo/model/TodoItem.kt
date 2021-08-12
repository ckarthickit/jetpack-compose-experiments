package com.kartdroid.composecodelabs.statecodelab.todo.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.graphics.vector.ImageVector
import com.kartdroid.composecodelabs.R.string
import java.util.UUID

data class TodoItem(
    val task: String,
    val iconWithDesc : TodoIcon,
    // since the user may generate identical tasks, give them each a unique ID
    val id: UUID = UUID.randomUUID()
)

enum class TodoIcon(val icon: ImageVector, @StringRes val contentDescription: Int){
    Square(Icons.Default.AccountBox, string.icon_desc_account),
    Done(Icons.Default.Done, string.icon_desc_done),
    Call(Icons.Default.Call , string.icon_desc_done)
}


fun generateRandomTodoItem(): TodoItem {
    val message = listOf(
        "Learn compose",
        "Learn state",
        "Build dynamic UIs",
        "Learn Unidirectional Data Flow",
        "Integrate LiveData",
        "Integrate ViewModel",
        "Remember to savedState!",
        "Build stateless composables",
        "Use state from stateless composables"
    ).random()
    val icon = TodoIcon.values().random()
    return TodoItem(message, icon)
}
