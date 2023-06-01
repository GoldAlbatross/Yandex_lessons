package com.example.sprint_18_tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sprint_18_tablayout.databinding.ActivityMainBinding
import com.example.sprint_18_tablayout.databinding.NumberFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager.adapter =
            NumbersViewPagerAdapter(supportFragmentManager, lifecycle)

        tabMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            when(position) {
//                0 -> tab.text = "HOME 1"
//                1 -> tab.text = "Вкладка 2"
//                2 -> tab.text = "Вкладка 3"
//            }
                tab.text = "Вкладка ${position + 1}"
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }

}