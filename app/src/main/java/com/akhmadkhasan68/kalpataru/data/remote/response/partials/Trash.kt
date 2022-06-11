package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName

data class Trash(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("jenis")
    val jenis: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
