package com.example.kotikprob.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.kotikprob.common.base.BaseRepository
import com.example.kotikprob.data.network.apiservises.LocationApiService
import com.example.kotikprob.data.network.dtos.location.Location
import com.example.kotikprob.data.network.pagingsources.LocationPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(){

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

    fun fetchLocation(id: Int)=  doRequest{
        service.fetchLocation(id)
    }
}