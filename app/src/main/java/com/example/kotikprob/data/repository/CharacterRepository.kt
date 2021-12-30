package com.example.kotikprob.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotikprob.common.base.BaseRepository
import com.example.kotikprob.data.network.apiservises.CharacterApiService
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.data.network.pagingsources.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository (
    private val service: CharacterApiService
) : BaseRepository() {

    fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).flow
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}