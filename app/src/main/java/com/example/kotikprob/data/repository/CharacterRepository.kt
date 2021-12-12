package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotikprob.data.network.apiService.CharacterApiService
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.data.network.pagingsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService
) {

    fun fetchCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }
}