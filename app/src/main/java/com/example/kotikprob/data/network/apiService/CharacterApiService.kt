package com.example.kotikprob.data.network.apiService

import com.example.kotikprob.data.network.dtos.RickAndMortyResponse
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.ui.fragments.character.CharacterFragment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<Character>
}