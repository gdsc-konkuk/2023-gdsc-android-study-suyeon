package kr.ac.konkuk.gdsc.gdscsuyeon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myname = intent.getStringExtra("nickname")
        if(myname != null) {
            binding.myName.text = myname.toString()
        }

        binding.myInfoChangeBtn.setOnClickListener {
            navigateToEditActivity(binding.myName.text.toString())
        }
    }

    private fun navigateToEditActivity(nickname: String) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("nickname", nickname)
        startActivity(intent)
    }
}