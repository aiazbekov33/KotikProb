package com.example.kotikprob.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotikprob.common.base.BaseRepository
import com.example.kotikprob.data.network.apiservises.LocationApiService
import com.example.kotikprob.data.network.dtos.location.Location
import com.example.kotikprob.data.network.pagingsources.LocationPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) : BaseRepository() {

    fun fetchLocations(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }
}