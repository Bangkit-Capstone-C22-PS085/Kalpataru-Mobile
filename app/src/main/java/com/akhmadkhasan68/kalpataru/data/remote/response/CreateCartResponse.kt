package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataCart
import com.google.gson.annotations.SerializedName

data class CreateCartResponse(
    @field:SerializedName("data")
    val data: DataCart,

    @field:SerializedName("message")
    val message: String? = null
)