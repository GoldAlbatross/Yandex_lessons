package com.example.sprint18_fragments3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sprint18_fragments3.databinding.FragmentLoginSuccessBinding

class LoginSuccessFragment : Fragment() {

    private var _binding: FragmentLoginSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }
}