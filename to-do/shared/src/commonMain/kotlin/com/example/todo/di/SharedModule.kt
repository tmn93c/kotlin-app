package com.example.todo.di

import com.example.todo.viewmodel.TodoViewModel
import org.example.todo.db.DatabaseDriverFactory
import org.example.todo.repository.TodoRepositoryImpl


object SharedModule {
    fun provideViewModel(driverFactory: DatabaseDriverFactory): TodoViewModel {
        val repository = TodoRepositoryImpl(driverFactory)
        return TodoViewModel(repository)
    }
}