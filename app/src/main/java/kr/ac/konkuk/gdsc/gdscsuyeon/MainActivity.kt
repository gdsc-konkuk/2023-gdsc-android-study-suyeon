package kr.ac.konkuk.gdsc.gdscsuyeon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.getStringExtra("nickname") != null){
            binding.myName.text = intent.getStringExtra("nickname")
        }
    }
}