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
        binding.myName.text = intent.getStringExtra("nickname")
        binding.ivBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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