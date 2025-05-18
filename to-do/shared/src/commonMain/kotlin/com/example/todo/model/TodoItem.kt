package org.example.todo.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.random.Random

data class TodoItem(
    val id: String = Random.nextInt().toString(),
    val title: String,
    val isDone: Int,
    val createdAt: Instant = Clock.System.now()
)
