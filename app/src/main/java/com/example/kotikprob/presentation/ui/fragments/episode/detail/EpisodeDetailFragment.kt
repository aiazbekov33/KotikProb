package com.example.kotikprob.presentation.ui.fragments.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.databinding.FragmentEpisodeDetailBinding
import com.example.kotikprob.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

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
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
        setupRequests()
    }

    private fun setupRequests() = with(binding) {
        viewModel.episodeState.subscribe {
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
                        binding.textAirDateEpisodeDetail.text = data.air_date
                        binding.textEpisodeDetail.text = data.episode
                        binding.textCreateDetail.text = data.created
                        binding.textIdEpisodeDetail.text = data.name
                    }
                }
            }
        }
    }
}