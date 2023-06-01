package com.example.sprint_18_tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sprint_18_tablayout.databinding.NumberFragmentBinding

class NumberFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        NumberFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.number.text = arguments?.getInt(NUMBER,999).toString()
    }
    companion object {
        private const val NUMBER = "number"
        fun newFragment(number: Int) = NumberFragment().apply {
            arguments = bundleOf(NUMBER to number)
        }
    }

}