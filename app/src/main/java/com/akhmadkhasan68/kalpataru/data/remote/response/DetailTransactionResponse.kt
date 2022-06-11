package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.google.gson.annotations.SerializedName

data class DetailTransactionResponse(
	@field:SerializedName("data")
	val data: DataTransactions? = null,

	@field:SerializedName("message")
	val message: String? = null
)

