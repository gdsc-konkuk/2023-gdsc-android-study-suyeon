package kr.ac.konkuk.gdsc.gdscsuyeon.ui.mypage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.home.HomeViewModel

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesForUrl: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var editorForUrl: SharedPreferences.Editor
    private lateinit var userName: String
    private lateinit var profileurl: String
    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "MyPageFragment binding is null" }
    private val viewModel by viewModels<MyPageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("nickname1", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sharedPreferencesForUrl = requireContext().getSharedPreferences("profileUrl", MODE_PRIVATE)
        editorForUrl = sharedPreferencesForUrl.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        lifecycleScope.launch {
            viewModel.loadDoneTodoNum() // 비동기 호출
            // 로딩이 완료된 후에 값을 가져와서 사용
            binding.completedTodo.text =
                getString(R.string.completed_todo_count, viewModel.doneTodoNum.value)
        }

        val nickname = sharedPreferences.getString("mypagename", "fail")
        if (nickname != null) {
            binding.myName.text = nickname.toString()
        } else {
            binding.myName.text = "바보"
        }
        val url = sharedPreferencesForUrl.getString("urlFromEditActivity", "fail").toString()
        profileGlide(url)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    userName = result.data?.getStringExtra("editname").toString()
                    profileurl = result.data?.getStringExtra("profileUrl").toString()
                    editor.putString("mypagename", userName)
                    editor.commit()
                    editorForUrl.putString("myprofileurl", profileurl)
                    editorForUrl.commit()
                    binding.myName.text = userName
                    profileGlide(profileurl)
                }
            }

        binding.myInfoChangeBtn.setOnClickListener {
            val intent = Intent(requireContext(), EditActivity::class.java)
            resultLauncher.launch(intent)
        }
        return binding.root
    }

    private fun profileGlide(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.konkuklogo)
            .error(R.drawable.konkuklogo)
            .fallback(R.drawable.konkuklogo)
            .circleCrop()
            .into(binding.profilePicture)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}