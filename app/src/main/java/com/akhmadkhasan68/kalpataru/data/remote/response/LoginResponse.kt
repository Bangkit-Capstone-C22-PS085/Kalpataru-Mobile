package com.akhmadkhasan68.kalpataru.data.remote.response

import com.akhmadkhasan68.kalpataru.data.remote.response.partials.Token
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: Token? = null
)

