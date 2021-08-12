package com.kartdroid.composecodelabs.statecodelab.todo.videmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.kartdroid.composecodelabs.statecodelab.todo.model.TodoItem

class TodoViewModel : ViewModel() {

    var items: SnapshotStateList<TodoItem> = mutableStateListOf<TodoItem>()
        private set

    fun addItem(item: TodoItem) {
        items.add(item)
    }
}