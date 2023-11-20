package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity

class MyPageFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userName: String
    private var _binding: FragmentMyPageBinding?= null
    private val binding
        get() = requireNotNull(_binding) {"MyPageFragment binding is null"}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("nickname1", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        val nickname = sharedPreferences.getString("mypagename", "fail")
        if (nickname != null) {
            binding.myName.text = nickname.toString()
        } else {
            binding.myName.text = "바보"
        }

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    userName = result.data?.getStringExtra("editname").toString()
                    editor.putString("mypagename", userName)
                    editor.commit()
                    binding.myName.text = userName
                }
            }

        binding.myInfoChangeBtn.setOnClickListener {
            val intent = Intent(requireContext(), EditActivity::class.java)
            resultLauncher.launch(intent)
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}