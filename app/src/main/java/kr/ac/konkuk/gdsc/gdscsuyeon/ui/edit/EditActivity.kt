package kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit

import android.annotation.SuppressLint
import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editViewModel = ViewModelProvider(this)[EditViewModel::class.java]

        binding.editVM = editViewModel
        binding.lifecycleOwner = this
        updateNickname()
        toHideKeyboard()
        initBackBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.ivBackButton.setOnClickListener {
            Log.d("TAG", "EditActiviy에서 전달 viewModel data ${editViewModel.currentName.value}")
            val intent = Intent()
            intent.putExtra("nickname", editViewModel.currentName.value)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
    private fun updateNickname() {

        binding.storage.setOnClickListener {
            val userInput = binding.editNickname.text
            if (userInput.isNotBlank()) {
                editViewModel.updateValue(ActionType.EDIT, userInput.toString())
                Log.d("TAG", "EditActiviy에서 현재 viewModel data ${editViewModel.currentName.value}")
                binding.editNickname.text.clear()
            } else {
                showSnackbar("입력값이 없습니다.")
            }
        }
        editViewModel.currentName.observe(this) {name ->
            binding.myName.text = name
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