package kr.ac.konkuk.gdsc.gdscsuyeon.domain

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodo(): Flow<List<TodoItem>>

    suspend fun insertTodo(todo: TodoItem)

    suspend fun updateTodo(todo: TodoItem)

    suspend fun deleteTodo(todo: TodoItem)
}