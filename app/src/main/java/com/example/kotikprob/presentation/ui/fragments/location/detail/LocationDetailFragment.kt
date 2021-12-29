package com.example.kotikprob.presentation.ui.fragments.location.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.databinding.FragmentLocatioinDetailBinding
import com.example.kotikprob.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocatioinDetailBinding>(R.layout.fragment_locatioin_detail) {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocatioinDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocatioinDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
        setupRequests()
    }

    private fun setupRequests() = with(binding) {
        viewModel.locationState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            groupMain.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    Toast.makeText(requireContext(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    it.data?.let { data ->
                        binding.textId.text = data.id.toString()
                        binding.txtNameLocationDetail.text = data.name
                        binding.textLocationDetail.text = data.url
                        binding.textTypeLocationDetail.text = data.type
                        binding.textIdLocationDetail.text = data.dimension
                    }
                }
            }
        }
    }
}