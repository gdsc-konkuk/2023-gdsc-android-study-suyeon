package kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.internal.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kr.ac.konkuk.gdsc.gdscsuyeon.MainActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
//    private val viewModel: EditViewModel by viewModels()
    private lateinit var editViewModel: EditViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editViewModel = ViewModelProvider(this)[EditViewModel::class.java]

        binding.editVM = editViewModel
        binding.lifecycleOwner = this

        sharedPreferences = getSharedPreferences("nickname", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        var nickname = sharedPreferences.getString("name", "fail")
        if (nickname == null) {
            nickname = sharedPreferences.getString("name1", "fail1")
        }
        editViewModel.updateValue(ActionType.START, nickname.toString())
        binding.myName.text = nickname.toString()

        updateNickname()
        toHideKeyboard()
        initBackBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.ivBackButton.setOnClickListener {
            val intent = Intent()
            editor.putString("name1", editViewModel.currentName.value)
            editor.apply()
            intent.putExtra("editname", editViewModel.currentName.value)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
    private fun updateNickname() {

        binding.storage.setOnClickListener {
            val userInput = binding.editNickname.text
            if (userInput.isNotBlank()) {
                editViewModel.updateValue(ActionType.EDIT, userInput.toString())
                editor.putString("name", userInput.toString())
                editor.apply()
                binding.myName.text = userInput
                binding.editNickname.text.clear()
            } else {
                showSnackbar("입력값이 없습니다.")
            }
        }
    }
    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
    @SuppressLint("RestrictedApi")
    private fun toHideKeyboard() {
        binding.root.setOnClickListener {
            ViewUtils.hideKeyboard(currentFocus ?: View(this))
            binding.editNickname.clearFocus()
        }
    }
}