package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotikprob.common.base.BaseRepository
import com.example.kotikprob.data.network.apiservises.CharacterApiService
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.data.network.pagingsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
        private val service: CharacterApiService
) : BaseRepository() {

    fun fetchCharacters(): LiveData<PagingData<Character>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 5,
                ),
                pagingSourceFactory = {
                    CharacterPagingSource(service)
                }
        ).liveData
    }

    var character: LiveData<Character>? = null

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}