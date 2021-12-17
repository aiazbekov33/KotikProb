package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.kotikprob.data.network.apiService.LocationApiService
import com.example.kotikprob.data.network.dtos.location.Location
import com.example.kotikprob.data.network.pagingsources.LocationPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) {

    fun fetchLocations(): LiveData<PagingData<Location>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }

    var location: LiveData<Location>? = null

    fun fetchLocation(id: Int): MutableLiveData<Location?>{
        val _location = MutableLiveData<Location?>()
        service.fetchLocation(id).enqueue(object : Callback<Location>{
            override fun onResponse(call: Call<Location>, response: Response<Location>) {
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                _location.value = null
            }

        })
        return _location
    }
}