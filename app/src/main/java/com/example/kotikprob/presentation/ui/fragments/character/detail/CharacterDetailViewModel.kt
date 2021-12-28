package com.example.kotikprob.presentation.ui.fragments.character.detail

import com.example.kotikprob.common.base.BaseViewModel
import com.example.kotikprob.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel(){

    fun fetchCharacter(id:Int) = repository.fetchCharacter(id)
}