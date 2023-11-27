package kr.ac.konkuk.gdsc.gdscsuyeon.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoRepository
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {

    fun insertTodo(item: TodoItem) {
        viewModelScope.launch {
            todoRepository.insertTodo(item)
        }
    }
}