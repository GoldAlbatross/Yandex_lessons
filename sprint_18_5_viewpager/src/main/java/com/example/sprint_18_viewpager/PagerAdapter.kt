package com.example.sprint_18_viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(hostFragment: Fragment) : FragmentStateAdapter(hostFragment) {

    // Тут указываем то количество элементов, которое хотим увидеть в нашем ViewPager
    override fun getItemCount(): Int = 2

    /**
     * Тут можно возвращать фрагменты одного класса, а можно абcолютно разные. Надо смотреть на
     * конкретную задачу. Главное подумать, какой фрагмент вы хотите видеть под определённой
     * позицией
     *
     * Если мы используем один фрагмент для всех страниц, то можно передавать внутрь него position,
     * как argument. И в зависимости от неё как-то менять интерфейс фрагмента
     */
    override fun createFragment(position: Int): Fragment {
        return if (position == 0) NestedFragmentA() else NestedFragmentB()
    }
}