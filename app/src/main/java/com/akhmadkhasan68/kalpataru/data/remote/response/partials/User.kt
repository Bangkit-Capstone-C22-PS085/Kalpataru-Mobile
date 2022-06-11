package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("member")
    val member: Member? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("operator")
    val operator: Operator? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
