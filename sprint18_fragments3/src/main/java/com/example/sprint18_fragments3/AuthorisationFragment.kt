package com.example.sprint18_fragments3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.sprint18_fragments3.databinding.StartScreenBinding

class AuthorisationFragment : Fragment() {

    private val binding by lazy(
        LazyThreadSafetyMode.NONE) { StartScreenBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {

            registration.setOnClickListener {
                activity?.supportFragmentManager?.commit {
                    replace<RegistrationFragment>(R.id.fragment_container_view)
                    addToBackStack("registration")
                    setReorderingAllowed(true)
                }
            }

            login.setOnClickListener {
                activity?.supportFragmentManager?.commit {
                    replace<LoginSuccessFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                }
            }
        }

    }
}