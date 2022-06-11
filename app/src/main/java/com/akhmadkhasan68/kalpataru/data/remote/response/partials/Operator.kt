package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName


data class Operator(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("address")
    val address: Any? = null,

    @field:SerializedName("balance")
    val balance: Int? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("long")
    val jsonMemberLong: Any? = null,

    @field:SerializedName("lat")
    val lat: Any? = null,

    @field:SerializedName("points")
    val points: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

