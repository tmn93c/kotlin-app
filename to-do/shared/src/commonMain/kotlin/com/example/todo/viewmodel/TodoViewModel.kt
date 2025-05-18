package com.example.todo.viewmodel

import app.cash.sqldelight.db.Closeable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.todo.model.TodoItemReponse
import org.example.todo.repository.TodoRepositoryImpl

class TodoViewModel(
    private val repository: TodoRepositoryImpl
) {
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    private val _todos = MutableStateFlow<List<TodoItemReponse>>(emptyList())
    val todos: StateFlow<List<TodoItemReponse>> get() = _todos
    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    fun observeTodos(): StateFlow<List<TodoItemReponse>> = todos

    fun addTodo(title: String) {
        repository.addTodo(title)
    }

    fun removeTodo(id: String) {
        repository.removeTodo(id)
    }

    fun loadTodos() {
        scope.launch {
            repository.getAllTodos().collect { todos ->
                _todos.value = todos
            }
        }
    }

    fun observeTodos(callback: (List<TodoItemReponse>) -> Unit): Closeable {
        val job = CoroutineScope(Dispatchers.Main).launch {
            todos.collect {
                callback(it)
            }
        }

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }

    fun clear() {
        scope.cancel() // hủy coroutine khi ViewModel không còn dùng nữa
    }
}
