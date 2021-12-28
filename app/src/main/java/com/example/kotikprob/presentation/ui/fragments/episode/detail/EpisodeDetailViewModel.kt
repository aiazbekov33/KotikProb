package com.example.kotikprob.presentation.ui.fragments.episode.detail

import com.example.kotikprob.common.base.BaseViewModel
import com.example.kotikprob.data.repository.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun  fetchEpisode(id: Int) = repository.fetchEpisode(id)
    }
