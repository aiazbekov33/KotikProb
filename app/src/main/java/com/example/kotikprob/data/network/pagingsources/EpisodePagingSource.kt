package com.example.kotikprob.data.network.pagingsources

import com.example.kotikprob.common.base.BasePagingSource
import com.example.kotikprob.data.network.apiservises.EpisodeApiService
import com.example.kotikprob.data.network.dtos.episode.Episode

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<Episode>({ position ->
    service.fetchEpisodes(position)
})