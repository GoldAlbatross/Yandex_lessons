package com.example.sprint16_architecture.core.ui.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sprint16_architecture.databinding.FragmentInfoBinding
import com.example.sprint16_architecture.databinding.FragmentMoviesBinding

class InfoFragment : Fragment() {

    private val binding by lazy { FragmentInfoBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }
}