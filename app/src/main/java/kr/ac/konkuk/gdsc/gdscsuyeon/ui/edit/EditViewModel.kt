package kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    EDIT,
    START
}

class EditViewModel : ViewModel() {

    private var _currentName = MutableLiveData<String>()
    private var _photoUrl = MutableLiveData<String>()

    val currentName: LiveData<String>
        get() = _currentName
    val photoUrl: LiveData<String>
        get() = _photoUrl

    fun updateValue(actionType: ActionType, input: String) {
        when (actionType) {
            ActionType.START, ActionType.EDIT -> _currentName.value = input
        }
    }

    fun updateUrl(input: String) {
        _photoUrl.value = input
    }
}