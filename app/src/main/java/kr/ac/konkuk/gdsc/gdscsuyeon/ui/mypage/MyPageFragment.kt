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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.data.Todo
import kr.ac.konkuk.gdsc.gdscsuyeon.data.TodoDatabase
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditActivity
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.edit.EditViewModel

class MyPageFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userName: String
    private lateinit var db: TodoDatabase
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

        db = TodoDatabase.getInstance(this)!!
        var completedTodoNum = 0
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().getAllTodo().map { todo ->
                if (todo.isDone) {
                    completedTodoNum++
                }
            }
            withContext(Dispatchers.Main) {
                binding.completedTodo.text = "완료 투두: ${completedTodoNum}개"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}