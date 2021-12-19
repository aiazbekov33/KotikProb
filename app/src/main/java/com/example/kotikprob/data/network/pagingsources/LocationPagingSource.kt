package com.example.kotikprob.data.network.pagingsources

import com.example.kotikprob.common.base.BasePagingSource
import com.example.kotikprob.data.network.apiservises.LocationApiService
import com.example.kotikprob.data.network.dtos.location.Location

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<Location>({ position ->
    service.fetchLocations(position)
})