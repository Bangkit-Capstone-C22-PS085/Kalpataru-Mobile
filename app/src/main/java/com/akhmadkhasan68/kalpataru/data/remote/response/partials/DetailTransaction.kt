package com.akhmadkhasan68.kalpataru.data.remote.response.partials

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTransaction(

    @field:SerializedName("transaction_id")
    val transactionId: Int? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

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
) : Parcelable