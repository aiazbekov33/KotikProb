package com.example.kotikprob.ui.fragments.location

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.databinding.FragmentLocationBinding
import com.example.kotikprob.ui.adapter.location.LocationAdapter
import com.example.kotikprob.ui.adapter.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    private lateinit var binding: FragmentLocationBinding
    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(this::setupListeners)
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupObservers() {
        viewModel.fetchLocations().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                locationAdapter.submitData(it)
            }
        }
    }

    override fun initialize() {
        setupLocationRecycler()
    }

    private fun setupLocationRecycler() = with(binding) {
        recyclerLocation.layoutManager = LinearLayoutManager(context)
        recyclerLocation.adapter = locationAdapter.withLoadStateFooter(LoadStateAdapter {
            locationAdapter.retry()
        })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerLocation.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            binding.locationSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun setupRequest() {
        viewModel.fetchLocations().observe(viewLifecycleOwner, {
            this.lifecycleScope.launch {
                locationAdapter.submitData(it)
            }
        })
        Toast.makeText(requireContext(), "location", Toast.LENGTH_LONG).show()
    }

    private fun setupListeners(id: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        )
    }

    override fun swiperefresh() {
        runnable = Runnable {
            binding.locationSwipeRefreshLayout.isRefreshing
        }
        handler.postDelayed(
            runnable, 3.toLong()
        )
    }
}