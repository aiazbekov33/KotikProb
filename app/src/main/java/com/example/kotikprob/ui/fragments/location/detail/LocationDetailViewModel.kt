package com.example.kotikprob.ui.fragments.location.detail

import androidx.lifecycle.ViewModel
import com.example.kotikprob.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    fun  fetchLocation(id: Int) = repository.fetchLocation(id)
    }
