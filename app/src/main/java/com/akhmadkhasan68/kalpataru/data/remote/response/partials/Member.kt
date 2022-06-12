package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("balance")
    val balance: Int? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("long")
    val long: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null,

    @field:SerializedName("points")
    val points: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
) : Parcelable

