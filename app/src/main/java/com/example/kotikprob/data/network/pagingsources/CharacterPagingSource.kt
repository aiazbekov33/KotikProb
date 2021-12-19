package com.example.kotikprob.data.network.pagingsources

import com.example.kotikprob.common.base.BasePagingSource
import com.example.kotikprob.data.network.apiservises.CharacterApiService
import com.example.kotikprob.data.network.dtos.character.Character

class CharacterPagingSource(
    private val service: CharacterApiService
) : BasePagingSource<Character>({ position ->
    service.fetchCharacters(position)
})