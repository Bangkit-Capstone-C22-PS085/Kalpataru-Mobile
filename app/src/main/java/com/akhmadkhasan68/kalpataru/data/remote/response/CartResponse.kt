package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataCart
import com.google.gson.annotations.SerializedName

data class CartResponse(
	@field:SerializedName("data")
	val data: List<DataCart?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)
