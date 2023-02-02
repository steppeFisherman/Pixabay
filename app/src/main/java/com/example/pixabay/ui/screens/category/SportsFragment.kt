package com.example.pixabay.ui.screens.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentSportsBinding
import com.example.pixabay.domain.model.ErrorType
import com.example.pixabay.ui.BaseFragment
import com.example.pixabay.ui.MainViewModel
import com.example.pixabay.ui.adapters.SportFragmentAdapter
import com.example.pixabay.ui.adapters.SportListener
import com.example.pixabay.ui.model.DataUi
import com.example.pixabay.utils.DialogShow
import com.example.pixabay.utils.LoadImage
import com.example.pixabay.utils.snackLong
import com.example.pixabay.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFragment : BaseFragment<FragmentSportsBinding>() {

    private val vm by activityViewModels<MainViewModel>()
    private val dialogShow = DialogShow.Base()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSportsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SportFragmentAdapter(object : SportListener {
            override fun invoke(data: DataUi.Hit) {
                val str = data.largeImageURL
                val bundle = Bundle()
                bundle.putString(ARGS_KEY, str)
                findNavController().navigate(
                    R.id.action_sportsFragment_to_sportDetailsFragment,
                    bundle
                )
            }
        }, LoadImage.Base())

        binding.sportsFragmentRv.adapter = adapter

        vm.sports.observe(viewLifecycleOwner, object : Observer<DataUi> {
            override fun onChanged(dataUi: DataUi) {
                if (dataUi.sports.isNotEmpty()) {
                    binding.progressBarSports.visible(false)
                    adapter.submitList(dataUi.sports)
                }
            }
        })

        vm.errorSports.observe(viewLifecycleOwner, object : Observer<ErrorType> {
            override fun onChanged(errorType: ErrorType) {
                binding.progressBarSports.visible(false)
                when (errorType.ordinal) {
                    0 -> view.snackLong(R.string.unknown_host_exception_message)
                    1 -> view.snackLong(R.string.http_exception_message)
                    2 -> dialogShow.show(view.context)
                    3 -> view.snackLong(R.string.generic_exception_message)
                }
            }
        })
    }

    companion object {
        const val ARGS_KEY = "sport"
    }
}