package com.example.kotikprob.presentation.ui.fragments.episode

import android.os.Bundle
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
import com.example.kotikprob.databinding.FragmentEpisodeBinding
import com.example.kotikprob.presentation.ui.adapter.episode.EpisodeAdapter
import com.example.kotikprob.presentation.ui.adapter.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    private lateinit var binding: FragmentEpisodeBinding
    private val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this::setupListeners)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun initialize() = with(binding) {
        setupEpisodeRecycler()
    }

    private fun setupEpisodeRecycler() = with(binding) {
        recyclerEpisode.layoutManager = LinearLayoutManager(context)
        recyclerEpisode.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter {
            episodeAdapter.retry()
        })
        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            episodeSwipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collect {
                episodeAdapter.submitData(it)

            }
        }
        Toast.makeText(requireContext(), "Episode", Toast.LENGTH_LONG).show()
    }

    private fun setupListeners(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                id
            )
        )
    }

    override fun swipeRefresh() {
        binding.episodeSwipeRefreshLayout.setOnRefreshListener { episodeAdapter.refresh() }
    }
}

