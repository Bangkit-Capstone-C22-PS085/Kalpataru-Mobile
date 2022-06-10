package com.akhmadkhasan68.kalpataru.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: Token? = null
)

data class Token(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null
)

data class Member(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("address")
	val address: Any? = null,

	@field:SerializedName("balance")
	val balance: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Any? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("points")
	val points: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
