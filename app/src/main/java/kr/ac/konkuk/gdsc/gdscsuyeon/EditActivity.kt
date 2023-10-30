package kr.ac.konkuk.gdsc.gdscsuyeon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.storage.setOnClickListener {
            if (binding.editNickname.length() == 0) {
                Toast.makeText(this, "닉네임은 빈칸일 수 없습니다", Toast.LENGTH_SHORT).show()
            }else{
                binding.myName.text = binding.editNickname.text
                binding.editNickname.text.clear()
            }
        }
    }
}