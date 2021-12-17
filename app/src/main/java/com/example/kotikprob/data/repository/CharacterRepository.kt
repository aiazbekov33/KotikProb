package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotikprob.data.network.apiService.CharacterApiService
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.data.network.pagingsources.CharacterPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService
) {

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

    fun fetchCharacter(id: Int): MutableLiveData<Character?> {
        val _character = MutableLiveData<Character?>()
        service.fetchCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                _character.value = response.body()
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                _character.value =null
            }
        })
        return _character
    }
}