package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
    private var _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>>
        get() = _todoList
    init {
        viewModelScope.launch {
            _todoList.value = todoRepository.getAllTodo().first().map { it }
        }
    }

    fun updateDoneBtn(item: TodoItem) {
        viewModelScope.launch {
            val updatedItem = item.copy(isDone = !item.isDone)
            todoRepository.updateTodo(updatedItem)
            val updatedList = _todoList.value.map { todoItem ->
                if (todoItem.id == item.id) {
                    updatedItem
                } else {
                    todoItem
                }
            }
            _todoList.value = updatedList
        }
    }
}