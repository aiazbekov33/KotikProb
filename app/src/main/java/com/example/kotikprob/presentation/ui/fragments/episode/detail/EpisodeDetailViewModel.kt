package com.example.kotikprob.presentation.ui.fragments.episode.detail

import com.example.kotikprob.common.base.BaseViewModel
import com.example.kotikprob.data.network.dtos.episode.Episode
import com.example.kotikprob.data.repository.EpisodeRepository
import com.example.kotikprob.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EpisodeDetailViewModel(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    private val _episodeState = MutableStateFlow<UIState<Episode>>(UIState.Loading())
    val episodeState: StateFlow<UIState<Episode>> = _episodeState

    fun fetchEpisode(id: Int) {
        _episodeState.subscribeTo {
            repository.fetchEpisode(id)
        }
    }
}
