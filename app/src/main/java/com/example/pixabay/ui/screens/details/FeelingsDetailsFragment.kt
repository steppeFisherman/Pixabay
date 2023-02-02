package com.example.pixabay.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentFeelingsDetailsBinding
import com.example.pixabay.ui.BaseFragment
import com.example.pixabay.ui.screens.category.FeelingsFragment
import com.example.pixabay.utils.LoadImage
import com.example.pixabay.utils.MyBitmap
import com.example.pixabay.utils.snackLong

class FeelingsDetailsFragment : BaseFragment<FragmentFeelingsDetailsBinding>() {

    private val glide = LoadImage.Base()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFeelingsDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getString(FeelingsFragment.ARGS_KEY).toString()
        glide.load(requireContext(), binding.imgFullScreenFeelings, bundle)

        binding.btnInstallFeelings.setOnClickListener {
            Glide.with(view.context)
                .asBitmap()
                .load(bundle)
                .into(object : MyBitmap(view.context) {})
            view.snackLong(R.string.was_set_up_on_desktop)
            findNavController().navigateUp()
        }
    }
}