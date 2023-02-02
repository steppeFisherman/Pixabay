package com.example.pixabay.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pixabay.databinding.FragmentSplashBinding
import com.example.pixabay.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIfUserIsLoggedIn(true)
    }

    /**
     * Some logic could be done here
     */
    private fun checkIfUserIsLoggedIn(loggedIn: Boolean) {
        if (loggedIn) {
        } else {
        }
    }
}

