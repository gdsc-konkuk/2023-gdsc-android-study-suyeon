package kr.ac.konkuk.gdsc.gdscsuyeon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myInfoChangeBtn.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }
}