package com.example.kotikprob.data.network.dtos.character

import com.example.kotikprob.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: OriginDto,
    @SerializedName("location")
    val location: SimpleLocationDto,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String,
) : IBaseDiffModel
