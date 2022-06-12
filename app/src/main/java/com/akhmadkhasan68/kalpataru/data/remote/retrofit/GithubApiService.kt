package com.akhmadkhasan68.kalpataru.data.remote.retrofit

import com.akhmadkhasan68.kalpataru.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface GithubApiService {
    //Auth
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("name") name : String,
        @Field("password") password : String,
        @Field("password_confirmation") password_confirmation : String,
        @Field("type") type : String,
    ) : Call<RegisterResponse>

    @GET("auth/me")
    fun me() : Call<MeResponse>

    //Transaction
    @GET("transactions")
    fun getTransactions() : Call<TransactionResponse>

    @GET("transactions/{id}")
    fun getTransactionDetail(
        @Path("id") id: Int
    ) : Call<DetailTransactionResponse>

    @FormUrlEncoded
    @POST("transactions")
    fun createTransaction() : Call<MeResponse>

    //Cart
    @GET("cart")
    fun getCart() : Call<CartResponse>

    @GET("cart/{id}")
    fun getCartDetail(
        @Path("id") id : Int
    ): Call<DetailCartResponse>

    @FormUrlEncoded
    @POST("cart")
    fun createCart(
        @Field("trashes_id") trashes_id : Int,
        @Field("quantity") quantity : Int,
        @Field("subtotal") subtotal : String
    ) : Call<MeResponse>

    @FormUrlEncoded
    @PUT("cart/{id}")
    fun updateCart(
        @Path("id") id : Int,
        @Field("trashes_id") trashes_id : Int,
        @Field("quantity") quantity : Int,
        @Field("subtotal") subtotal : String
    ) : Call<MeResponse>

    @DELETE("cart/{id}")
    fun deleteCart(
        @Path("id") id : Int
    ) : Call<MeResponse>

    //Trash
    @FormUrlEncoded
    @POST("trash/get-price-prediction")
    fun getPredictionPrice(
        @Field("b64") b64 : String
    ): Call<TrashResponse>

    //Operator
    @PUT("operator/transactions/buy/{id}")
    fun buyTransaction(
        @Path("id") id : Int,
    ) : Call<MeResponse>

    @PUT("operator/transactions/complete/{id}")
    fun completeTransaction(
        @Path("id") id : Int,
    ) : Call<MeResponse>

    @GET("operator/transactions")
    fun getOperatorTransactions() : Call<TransactionResponse>

    @GET("operator/transactions/{id}")
    fun getOperatorTransactionDetail(
        @Path("id") id: Int
    ) : Call<DetailTransactionResponse>

    @FormUrlEncoded
    @PUT("auth/update-address")
    fun updateAddress(
        @Field("long") lon: Float,
        @Field("lat") lat: Float,
        @Field("address") address: String
    ):Call<MeResponse>
//
//    @FormUrlEncoded
//    @POST("register")
//    fun register(
//        @Field("name") name : String,
//        @Field("email") email : String,
//        @Field("password") password: String
//    ) : Call<RegisterResponse>
//
//    @GET("stories")
//    fun getStories() : Call<GetStoryResponse>
//
//    @Multipart
//    @POST("stories")
//    fun uploadImage(
//        @Part file: MultipartBody.Part,
//        @Part("description") description : RequestBody,
//    ): Call<FileUploadResponse>
}