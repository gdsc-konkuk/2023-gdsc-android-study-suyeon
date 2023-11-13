package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditViewModel

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("nickname1", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        val nickname = sharedPreferences.getString("mypagename", "fail")
        if(nickname != null) {
            binding.myName.text = nickname.toString()
        } else {
            binding.myName.text = "바보"
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result->
            if(result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(requireContext(), "수신 성공", Toast.LENGTH_SHORT).show()
                userName = result.data?.getStringExtra("editname").toString()
                Log.d("TAG", "myPageFragment : $userName")
                editor.putString("mypagename", userName)
                editor.commit()
                binding.myName.text = userName
            } else {
                Toast.makeText(requireContext(), "수신 실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.myInfoChangeBtn.setOnClickListener {
            val intent = Intent(requireContext(), EditActivity::class.java)
            resultLauncher.launch(intent)
        }
        return binding.root
    }

}