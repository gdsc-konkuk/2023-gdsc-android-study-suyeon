package kr.ac.konkuk.gdsc.gdscsuyeon.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentCreateBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.home.TodoAdapter

@AndroidEntryPoint
class CreateFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCreateBinding?= null
    private val binding
        get() = requireNotNull(_binding) {"CreateFragment binding is null"}
    private val viewModel by viewModels<CreateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }
        binding.storeBtn.setOnClickListener {
            val context = binding.addTodo.text.toString()
            binding.addTodo.text.clear()
            viewModel.insertTodo(TodoItem(id,context, false))
            dismiss()
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

