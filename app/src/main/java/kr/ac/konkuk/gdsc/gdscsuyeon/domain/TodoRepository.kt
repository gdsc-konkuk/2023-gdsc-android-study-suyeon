package kr.ac.konkuk.gdsc.gdscsuyeon.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.ac.konkuk.gdsc.gdscsuyeon.data.db.TodoEntity
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem
import javax.inject.Inject

interface TodoRepository {
    fun getAllTodo(): Flow<List<TodoItem>>

    suspend fun insertTodo(todo: TodoItem)

    suspend fun updateTodo(todo: TodoItem)

    suspend fun deleteTodo(todo: TodoItem)
}