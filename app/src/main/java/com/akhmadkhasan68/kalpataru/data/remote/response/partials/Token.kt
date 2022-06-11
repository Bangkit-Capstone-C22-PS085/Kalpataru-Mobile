package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName

data class Token(

    @field:SerializedName("access_token")
    val accessToken: String? = null,

    @field:SerializedName("refresh_token")
    val refreshToken: String? = null
)