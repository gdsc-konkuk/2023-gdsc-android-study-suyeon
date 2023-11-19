package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()
    private val binding
        get() = requireNotNull(_binding) { "HomeFragment binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoadapter = TodoAdapter(
            isTodoDoneClicked = {
                viewModel.updateDoneBtn(it)
            }
        )

        binding.recyclerView.adapter = todoadapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.todoList.collectLatest { todoList ->
                    Log.d("TAG", "inHomeFragment - lifecycle")
                    todoadapter.submitList(todoList.toMutableList())
                    Log.d("TAG", "todolist : ${todoList}")
                }
            }
        }
    }
    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}