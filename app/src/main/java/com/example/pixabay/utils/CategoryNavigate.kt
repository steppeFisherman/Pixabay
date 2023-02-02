package com.example.pixabay.utils

import androidx.navigation.NavController
import com.example.pixabay.R

interface CategoryNavigate {

    fun navigate(navController: NavController, position: Int)

    class Base : CategoryNavigate {
        override fun navigate(navController: NavController, position: Int) {
            when (position) {
                0 -> navController.navigate(R.id.action_global_feelingsFragment)
                1 -> navController.navigate(R.id.action_global_musicFragment)
                2 -> navController.navigate(R.id.action_global_sportsFragment)
                3 -> navController.navigate(R.id.action_global_animalsFragment)
                4 -> navController.navigate(R.id.action_global_industryFragment)
                5 -> navController.navigate(R.id.action_global_scienceFragment)
            }
        }
    }
}