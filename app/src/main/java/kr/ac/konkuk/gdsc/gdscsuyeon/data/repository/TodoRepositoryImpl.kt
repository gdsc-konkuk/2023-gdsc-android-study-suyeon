package kr.ac.konkuk.gdsc.gdscsuyeon.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ac.konkuk.gdsc.gdscsuyeon.data.db.TodoDao
import kr.ac.konkuk.gdsc.gdscsuyeon.data.db.TodoEntity
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) : TodoRepository {
    override fun getAllTodo(): Flow<List<TodoItem>> =
        todoDao.getAllTodo().map { list ->
            list.map { it.mapToItem() }
        }

    override suspend fun insertTodo(todo: TodoItem) {
        todoDao.insertTodo(todo.mapToEntity())
    }

    override suspend fun updateTodo(todo: TodoItem) {
        todoDao.updateTodo(todo.mapToEntity())
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        todoDao.deleteTodo(todo.mapToEntity())
    }

    private fun TodoEntity.mapToItem(): TodoItem {
        return TodoItem(id = id, todoContext = todoContext, isDone = isDone)
    }

    private fun TodoItem.mapToEntity(): TodoEntity {
        return TodoEntity(id = id, todoContext = todoContext, isDone = isDone)
    }
}