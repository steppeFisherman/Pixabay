package com.example.pixabay.utils

import com.example.pixabay.R
import com.example.pixabay.ui.model.Category


interface CategoryProvide {

    fun default(): List<Category>

    class Base : CategoryProvide {
        override fun default(): List<Category> = listOf(
            Category(0, "Чувства", R.drawable.feelings),
            Category(1, "Музыка", R.drawable.music),
            Category(2, "Спорт", R.drawable.sports),
            Category(3, "Животные", R.drawable.animals),
            Category(4, "Индустрия", R.drawable.industry),
            Category(5, "Наука", R.drawable.science)
        )
    }
}
