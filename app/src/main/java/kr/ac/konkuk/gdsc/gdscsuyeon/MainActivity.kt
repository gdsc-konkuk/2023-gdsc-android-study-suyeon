package kr.ac.konkuk.gdsc.gdscsuyeon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.ActivityMainBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.create.CreateFragment
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.home.HomeFragment
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedNavItem: Int = R.id.myPageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFragment(HomeFragment())
            selectedNavItem = R.id.homeFragment
        }


        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    showFragment(HomeFragment())
                }
                R.id.myPageFragment -> {
                    showFragment(MyPageFragment())
                }
                else -> {
                    showFragment(MyPageFragment())
                }
            }
            true
        }

        binding.createTodoBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom)
                .add(
                    R.id.framelayout,
                    CreateFragment()
                )
                .addToBackStack(null)
                .commit()
        }
    }
    private fun showFragment(fragment: Fragment) {
        // Fragment 표시를 위한 함수
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, fragment)
            .addToBackStack(null)
            .commit()
    }

}

