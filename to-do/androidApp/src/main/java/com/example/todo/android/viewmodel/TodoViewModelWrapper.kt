package com.example.todo.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.viewmodel.TodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.todo.model.TodoItemReponse
import javax.inject.Inject

@HiltViewModel
class TodoViewModelWrapper @Inject constructor(
    private val vm: TodoViewModel
) : ViewModel() {

    val todos : StateFlow<List<TodoItemReponse>> = vm.todos

    init {
        vm.loadTodos()
    }

    fun addTodo(title: String) {
        viewModelScope.launch {
            vm.addTodo(title)
            vm.loadTodos()
        }
    }

    fun removeTodo(id: String) {
        viewModelScope.launch {
            vm.removeTodo(id)
            vm.loadTodos()
        }
    }

}

