package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoRepository
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            TodoItem.dummytodo.forEach {
                todoRepository.insertTodo(it)
            }
        }
    }

    val todoList = todoRepository.getAllTodo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf(),
    )

    fun updateDoneBtn(item: TodoItem) {
        viewModelScope.launch {
            val updatedItem = item.copy(isDone = !item.isDone)
            todoRepository.updateTodo(updatedItem)
        }

        suspend fun getDoneTodoNum(): Int {
            return todoRepository.getAllTodo().first().count { it.isDone }
        }
    }
}