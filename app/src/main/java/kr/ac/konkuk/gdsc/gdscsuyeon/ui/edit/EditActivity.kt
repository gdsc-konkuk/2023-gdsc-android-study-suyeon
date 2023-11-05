package kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kr.ac.konkuk.gdsc.gdscsuyeon.MainActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        updateNickname()
        toHideKeyboard()
    }

    private fun updateNickname() {

        binding.storage.setOnClickListener {
            val userInput = binding.editNickname.text
            if (userInput.isNotBlank()) {
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