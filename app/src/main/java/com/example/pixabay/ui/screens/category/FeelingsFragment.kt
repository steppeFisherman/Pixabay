package com.example.pixabay.ui.screens.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentFeelingsBinding
import com.example.pixabay.ui.BaseFragment
import com.example.pixabay.ui.MainViewModel
import com.example.pixabay.ui.adapters.FeelingsFragmentAdapter
import com.example.pixabay.ui.adapters.FeelingsListener
import com.example.pixabay.ui.model.DataUi
import com.example.pixabay.utils.LoadImage
import com.example.pixabay.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeelingsFragment : BaseFragment<FragmentFeelingsBinding>() {

    private val vm by activityViewModels<MainViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFeelingsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FeelingsFragmentAdapter(object : FeelingsListener {
            override fun invoke(data: DataUi.Hit) {
                val str = data.largeImageURL
                val bundle = Bundle()
                bundle.putString(ARGS_KEY, str)
                findNavController().navigate(
                    R.id.action_feelingsFragment_to_feelingsDetailsFragment,
                    bundle
                )
            }
        }, LoadImage.Base())

        binding.feelingsFragmentRv.adapter = adapter

        vm.feelings.observe(viewLifecycleOwner, object : Observer<DataUi> {
            override fun onChanged(dataUi: DataUi) {
                if (dataUi.feelings.isNotEmpty()) {
                    binding.progressBarFeelings.visible(false)
                    adapter.submitList(dataUi.feelings)
                }
            }
        })
    }

    companion object {
        const val ARGS_KEY = "feelings"
    }
}