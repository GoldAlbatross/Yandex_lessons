package com.example.test_02_repeatonlificycle

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test_02_repeatonlificycle.databinding.FragmentScreenBinding
import kotlinx.coroutines.launch

class FlowFragment : Fragment() {
    private val binding by lazy(
        LazyThreadSafetyMode.NONE) { FragmentScreenBinding.inflate(layoutInflater) }

    //private val viewModel by viewModels<FlowViewModel>()
    private val viewModel by lazy { ViewModelProvider(this)[FlowViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect{
                        Log.d("qqq", "FlowFragment -> count: $it")
                        binding.content.text = it.toString()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("qqq", "FlowFragment -> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("qqq", "FlowFragment -> onPause")
    }
}

