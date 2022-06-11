package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName

data class DataTransactions(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("total")
    val total: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("member")
    val member: Member? = null,

    @field:SerializedName("user_id_operator")
    val userIdOperator: Any? = null,

    @field:SerializedName("details")
    val details: List<DetailTransaction?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("operator")
    val operator: Operator? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
