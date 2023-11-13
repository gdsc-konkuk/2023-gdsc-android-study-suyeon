package kr.ac.konkuk.gdsc.gdscsuyeon.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentCreateBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentMyPageBinding

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}