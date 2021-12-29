package com.example.kotikprob.presentation.ui.fragments.location.detail

import com.example.kotikprob.common.base.BaseViewModel
import com.example.kotikprob.data.network.dtos.location.Location
import com.example.kotikprob.data.repository.LocationRepository
import com.example.kotikprob.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<Location>>(UIState.Loading())
    val locationState: StateFlow<UIState<Location>> = _locationState

    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.fetchLocation(id)
        }
    }
}
