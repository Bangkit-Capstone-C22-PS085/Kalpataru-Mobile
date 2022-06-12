package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.Trash
import com.google.gson.annotations.SerializedName

data class TrashDetailResponse(
    @field:SerializedName("data")
    val data: Trash,

    @field:SerializedName("message")
    val message: String? = null
)
