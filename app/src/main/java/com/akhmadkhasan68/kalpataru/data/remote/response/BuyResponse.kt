package com.akhmadkhasan68.kalpataru.data.remote.response

import com.google.gson.annotations.SerializedName

data class BuyResponse(
    @field:SerializedName("data")
    val data: Array<Int>,

    @field:SerializedName("message")
    val message: String? = null
)
