package com.example.kotikprob.presentation.ui.fragments.character.detail

import com.example.kotikprob.common.base.BaseViewModel
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.data.repository.CharacterRepository
import com.example.kotikprob.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CharacterDetailViewModel (
    private val repository: CharacterRepository
) : BaseViewModel() {

    private val _characterState = MutableStateFlow<UIState<Character>>(UIState.Loading())
    val characterState: StateFlow<UIState<Character>> = _characterState

    fun fetchCharacter(id: Int) {
        _characterState.subscribeTo {
            repository.fetchCharacter(id)
        }
    }
}