package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import com.google.gson.annotations.SerializedName

data class DataCart(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("subtotal")
	val subtotal: Int? = null,

	@field:SerializedName("trashes_id")
	val trashesId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("trash")
	val trash: Trash? = null
)