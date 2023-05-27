package com.example.sprint18_fragments4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.sprint18_fragments4.databinding.FragmentANestedBinding

// Первый вложенный фрагмент
class NestedFragmentA : Fragment() {

    private val binding by lazy(
        LazyThreadSafetyMode.NONE) { FragmentANestedBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_child_container, NestedFragmentB())
            }
        }
    }
}