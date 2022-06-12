package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTransactions(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("total")
    val total: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("member")
    val member: User? = null,

    @field:SerializedName("user_id_operator")
    val userIdOperator: Int? = null,

    @field:SerializedName("details")
    val details: List<DetailTransaction>,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("operator")
    val operator: User? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
) : Parcelable
