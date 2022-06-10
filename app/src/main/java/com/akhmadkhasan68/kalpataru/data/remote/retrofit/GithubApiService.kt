package com.akhmadkhasan68.kalpataru.data.remote.retrofit

import com.akhmadkhasan68.kalpataru.data.remote.response.LoginResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface GithubApiService {
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