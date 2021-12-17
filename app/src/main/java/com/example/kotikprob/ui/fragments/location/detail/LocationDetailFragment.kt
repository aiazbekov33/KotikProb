package com.example.kotikprob.ui.fragments.location.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kotikprob.databinding.FragmentLocatioinDetailBinding
import com.example.kotikprob.databinding.FragmentLocatioinDetailBinding.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment : Fragment() {
    private val viewModel: LocationDetailViewModel by viewModels()
    private lateinit var binding: FragmentLocatioinDetailBinding
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.fetchLocation(args.id).observe(viewLifecycleOwner) {
                binding.textId.text = it?.id.toString()
                binding.txtNameLocationDetail.text = it?.name
                binding.textLocationDetail.text = it?.url
                binding.textTypeLocationDetail.text = it?.type
                binding.textIdLocationDetail.text = it?.dimension
        }
    }
}