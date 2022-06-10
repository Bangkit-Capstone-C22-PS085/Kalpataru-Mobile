package com.akhmadkhasan68.kalpataru.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String,
    val username: String,
    val email : String,
    val type : String,
    val name: String,
    val token: String,
    val isLogin: Boolean
) : Parcelable
