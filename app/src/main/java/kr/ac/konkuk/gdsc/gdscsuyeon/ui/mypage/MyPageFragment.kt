package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context.MODE_PRIVATE
import android.content.Intent
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
//    private lateinit var editViewModel: EditViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result->
            if(result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(requireContext(), "수신 성공", Toast.LENGTH_SHORT).show()
                binding.myName.text = result.data?.getStringExtra("nickname")
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