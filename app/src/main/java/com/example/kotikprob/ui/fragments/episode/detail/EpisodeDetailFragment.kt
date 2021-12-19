package com.example.kotikprob.ui.fragments.episode.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.common.resource.Resource
import com.example.kotikprob.databinding.FragmentEpisodeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRequests()
    }

    private fun setupRequests() = with(binding){
        viewModel.fetchEpisode(args.id).observe(viewLifecycleOwner,{
            progressBar.isVisible = it is Resource.Loading
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
                        binding.textAirDateEpisodeDetail.text = data.air_date
                        binding.textEpisodeDetail.text = data.episode
                        binding.textCreateDetail.text = data.created
                        binding.textIdEpisodeDetail.text = data.name
                    }
                }
            }
        })
    }
}