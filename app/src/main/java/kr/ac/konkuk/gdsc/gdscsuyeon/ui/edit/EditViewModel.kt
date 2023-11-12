package kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    EDIT,
    START
}

class EditViewModel : ViewModel() {

    private val _currentName = MutableLiveData<String>()

    val currentName: LiveData<String>
        get() = _currentName

    fun updateValue(actionType: ActionType, input: String) {
        when (actionType) {
            ActionType.START, ActionType.EDIT -> _currentName.value = input
        }
    }
}