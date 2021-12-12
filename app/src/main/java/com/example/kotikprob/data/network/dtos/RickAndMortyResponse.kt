package com.example.kotikprob.data.network.dtos

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse <T>(

    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: MutableList<T>

    )