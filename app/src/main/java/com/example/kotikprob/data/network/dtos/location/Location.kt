package com.example.kotikprob.data.network.dtos.location

import com.example.kotikprob.common.base.IBaseDiffModel

data class Location(
    override val id: Int,
    val name: String
): IBaseDiffModel
