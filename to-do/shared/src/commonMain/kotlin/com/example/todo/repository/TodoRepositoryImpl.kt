package org.example.todo.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.todo.db.TodoDatabase
import com.example.todo.db.TodoQueries
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.todo.db.DatabaseDriverFactory
import org.example.todo.model.TodoItem
import org.example.todo.model.TodoItemReponse

class TodoRepositoryImpl (driverFactory: DatabaseDriverFactory) : TodoRepository {
    private val db = TodoDatabase(
        driverFactory.createDriver()
    )
    private val queries: TodoQueries = db.todoQueries

    override fun getAllTodos(): Flow<List<TodoItemReponse>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO) // cần import kotlinx.coroutines.Dispatchers
            .map { list ->
                list.map {
                    TodoItemReponse(
                        id = it.id,
                        title = it.title,
                        createdAt = it.created_at
                    )
                }
            }
    }


    override fun addTodo(title: String) {
        val newTodo = TodoItem(title = title, isDone = 0)
        queries.insertTodo(newTodo.id, newTodo.title, 0)
    }

    override fun removeTodo(id: String) {
        queries.deleteTodo(id)
    }
}