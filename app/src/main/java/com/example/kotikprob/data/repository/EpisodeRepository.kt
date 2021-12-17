package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotikprob.data.network.apiService.EpisodeApiService
import com.example.kotikprob.data.network.dtos.episode.Episode
import com.example.kotikprob.data.network.pagingsources.EpisodePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
) {

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

    fun fetchEpisode(id: Int): MutableLiveData<Episode?> {
        val _episode = MutableLiveData<Episode?>()
        service.fetchEpisode(id).enqueue(object : Callback<Episode> {
            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                if (response.isSuccessful) {
                    _episode.value = response.body()
                }
            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                _episode.value = null
            }
        })
        return _episode
    }
}