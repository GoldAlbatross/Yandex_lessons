package com.example.sprint_18_tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumbersViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 30

    override fun createFragment(position: Int): Fragment {
//        return when(position) {
//            0 -> NumberFragment.newFragment(position + 1)
//            1 -> NumberFragment.newFragment(position + 1)
//            else -> NumberFragment.newFragment(position + 1)
//        }
        return NumberFragment.newFragment(position+1)
    }
}