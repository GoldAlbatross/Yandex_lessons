package com.example.sprint_18_viewpager

// Интерфейс для управления нашим ViewPager'ом из фрагментов, которые он содержит.

interface SelectPage {

    fun navigateTo(page: Int)
}