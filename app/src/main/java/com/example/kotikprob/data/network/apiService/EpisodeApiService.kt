package com.example.kotikprob.data.network.apiService

import com.example.kotikprob.data.network.dtos.RickAndMortyResponse
import com.example.kotikprob.data.network.dtos.episode.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {
    @GET("/api/episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int
    ) : RickAndMortyResponse<Episode>

    @GET("/api/episode/{id}")
    fun fetchEpisode(@Path("id") id: Int
    ) : Call<Episode>
}