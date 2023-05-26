package com.example.sprint18_fragments2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sprint18_fragments2.databinding.FragmentCitiesBinding

class CountriesFragment : Fragment() {

    private val countries = "Austria,Belarus,Bulgaria,Cambodia,Cameroon,Costa Rica"

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)

        binding.textView.text = countries
        return binding.root
    }
}