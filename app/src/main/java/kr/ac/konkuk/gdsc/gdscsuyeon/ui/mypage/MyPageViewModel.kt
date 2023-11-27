package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoRepository
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private var _doneTodoNum = MutableLiveData<String>()

    val doneTodoNum: LiveData<String>
        get() = _doneTodoNum

    suspend fun loadDoneTodoNum(): String {
        _doneTodoNum.value = todoRepository.getAllTodo().first().count { it.isDone }.toString()
        return doneTodoNum.value.toString()
    }
}