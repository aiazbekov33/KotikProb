package com.example.kotikprob.data.network.apiservises

import com.example.kotikprob.data.network.dtos.RickAndMortyResponse
import com.example.kotikprob.data.network.dtos.character.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/api/character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<Character>

    @GET("/api/character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): Character
}