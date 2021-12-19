package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotikprob.common.base.BaseRepository
import com.example.kotikprob.data.network.apiservises.EpisodeApiService
import com.example.kotikprob.data.network.dtos.episode.Episode
import com.example.kotikprob.data.network.pagingsources.EpisodePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(){

    fun fetchEpisodes(): LiveData<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }
    var episode: LiveData<Episode>? = null

    fun fetchEpisode(id: Int) = doRequest{
        service.fetchEpisode(id)

    }
}