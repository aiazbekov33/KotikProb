package com.example.kotikprob.presentation.ui.fragments.location.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.common.resource.Resource
import com.example.kotikprob.databinding.FragmentLocatioinDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocatioinDetailBinding>(R.layout.fragment_locatioin_detail) {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocatioinDetailBinding? = null
    private val binding get() = _binding!!
    private val args: LocationDetailFragmentArgs by navArgs()

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
        setupRequests()
    }

    private fun setupRequests() = with(binding) {
        viewModel.fetchLocation(args.id).observe(viewLifecycleOwner, {
            groupMain.isVisible = it !is Resource.Loading
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    it.data?.let { data ->
                        binding.textId.text = data.id.toString()
                        binding.txtNameLocationDetail.text = data.name
                        binding.textLocationDetail.text = data.url
                        binding.textTypeLocationDetail.text = data.type
                        binding.textIdLocationDetail.text = data.dimension
                    }
                }
            }

        })
    }
}