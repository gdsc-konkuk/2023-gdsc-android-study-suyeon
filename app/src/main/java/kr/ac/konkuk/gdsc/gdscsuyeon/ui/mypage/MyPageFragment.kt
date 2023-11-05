package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        binding.myInfoChangeBtn.setOnClickListener {
            val intent = Intent(requireActivity(), EditActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}