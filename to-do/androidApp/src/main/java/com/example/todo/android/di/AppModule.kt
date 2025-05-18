package com.example.todo.android.di

import android.content.Context
import com.example.todo.di.SharedModule
import com.example.todo.viewmodel.TodoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.example.todo.db.DatabaseDriverFactory
import org.example.todo.repository.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseDriverFactory(
        @ApplicationContext context: Context
    ): DatabaseDriverFactory = DatabaseDriverFactory(context)

    @Provides
    @Singleton
    fun provideTodoRepository(
        driverFactory: DatabaseDriverFactory
    ): TodoRepositoryImpl = TodoRepositoryImpl(driverFactory)

    @Provides
    @Singleton
    fun provideTodoViewModel(driverFactory: DatabaseDriverFactory): TodoViewModel {
        return SharedModule.provideViewModel(driverFactory)
    }
}
