package com.example.todo.repository

import kotlinx.coroutines.flow.Flow
import org.example.todo.model.TodoItemReponse

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItemReponse>>
    fun addTodo(title: String)
    fun removeTodo(id: String)
}
