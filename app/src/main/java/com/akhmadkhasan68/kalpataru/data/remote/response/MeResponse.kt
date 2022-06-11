package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.google.gson.annotations.SerializedName

data class MeResponse(

	@field:SerializedName("data")
	val detail: User? = null,

	@field:SerializedName("message")
	val message: String? = null
)
