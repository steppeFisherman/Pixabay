package com.example.pixabay.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentMainBinding
import com.example.pixabay.domain.model.ErrorType
import com.example.pixabay.ui.BaseFragment
import com.example.pixabay.ui.MainViewModel
import com.example.pixabay.ui.adapters.MainFragmentAdapter
import com.example.pixabay.utils.*
import com.example.pixabay.utils.connectivity.ConnectivityManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainFragmentAdapter.OnClick {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var snackBuilder: SnackBuilder

    private val vm by activityViewModels<MainViewModel>()
    private val category = CategoryProvide.Base().default()
    private val dialogShow = DialogShow.Base()
    private lateinit var navController: NavController
    private lateinit var snack: Snackbar

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snack = snackBuilder.buildSnackIndefinite(view)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        vm.errorFeelings.observe(viewLifecycleOwner, object : Observer<ErrorType> {
            override fun onChanged(errorType: ErrorType) {
                binding.progressBarMainFragment.visible(false)
                when (errorType.ordinal) {
                    0 -> view.snackLong(R.string.unknown_host_exception_message)
                    1 -> view.snackLong(R.string.http_exception_message)
                    2 -> dialogShow.show(view.context)
                    3 -> view.snackLong(R.string.generic_exception_message)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        categorySetup()
        navController.navigate(R.id.action_global_feelingsFragment)
        checkNetworks(connectivityManager) { isNetWorkAvailable ->
            if (!isNetWorkAvailable) snack.show() else snack.dismiss()
        }
    }

    private fun categorySetup() {
        binding.mainFragmentRv.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.HORIZONTAL,
            false
        )
        val adapter = MainFragmentAdapter(
            this,
            TextColorProvider.Base(requireActivity())
        )
        adapter.setData(category)
        binding.mainFragmentRv.adapter = adapter
        binding.mainFragmentRv.smoothScrollToPosition(0)
    }

    private fun checkNetworks(
        connectivityManager: ConnectivityManager,
        connected: (Boolean) -> Unit
    ): Boolean {
        var isNetWorkAvailable = true
        connectivityManager.isNetworkAvailable.observe(viewLifecycleOwner) {
            isNetWorkAvailable = it
            connected(isNetWorkAvailable)
        }
        return isNetWorkAvailable
    }

    override fun click(position: Int) {
        CategoryNavigate.Base().navigate(navController, position = position)
    }
}









