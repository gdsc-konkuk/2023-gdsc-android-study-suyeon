package kr.ac.konkuk.gdsc.gdscsuyeon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.internal.ViewUtils
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var myEditViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myEditViewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        binding.editVM = myEditViewModel
        binding.lifecycleOwner = this

        initNickname()
        binding.ivBackButton.setOnClickListener {
            navigateToMainActivity(myEditViewModel.currentName.value.toString())
        }
        updateNickname()
        toHideKeyboard()
    }
    private fun initNickname() {
        val myname = intent.getStringExtra("nickname")
        if (myname != null) {
            myEditViewModel.updateValue(ActionType.START, myname.toString())
        }
    }

    private fun navigateToMainActivity(nickname: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nickname", nickname)
        startActivity(intent)
        finish()
    }

    private fun updateNickname() {
        binding.storage.setOnClickListener {
            val userInput = binding.editNickname.text
            if (userInput.isNotBlank()) {
                myEditViewModel.updateValue(ActionType.EDIT, userInput.toString())
                binding.editNickname.text.clear()
            } else {
                showToast("입력값이 없습니다.")
            }
        }
        //LiveData 사용하여 데이터 변경 감지 및 UI 업데이트
        myEditViewModel.currentName.observe(this) { name ->
            binding.myName.text = name
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("RestrictedApi")
    private fun toHideKeyboard() {
        binding.root.setOnClickListener {
            ViewUtils.hideKeyboard(currentFocus ?: View(this))
            binding.editNickname.clearFocus()
        }
    }
}