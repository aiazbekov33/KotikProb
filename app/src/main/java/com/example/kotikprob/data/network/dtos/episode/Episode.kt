package com.example.kotikprob.data.network.dtos.episode

import com.example.kotikprob.common.base.IBaseDiffModel

data class Episode(
    override val id: Int,
    val name: String
): IBaseDiffModel
