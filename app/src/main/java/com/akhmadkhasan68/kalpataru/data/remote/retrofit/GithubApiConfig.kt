package com.akhmadkhasan68.kalpataru.data.remote.retrofit

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiConfig {
    companion object{
        private const val BASE_URL = "http://kalpataru-352211.et.r.appspot.com/api/"

        fun getGithubApiServices(pref: UserPreference? = null): GithubApiService {
            val loggingInterceptor = if(BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val networkInterceptor = Interceptor { chain : Interceptor.Chain ->
                val token = runBlocking {
                    pref?.getToken()?.first()
                }

                if (token != null) {
                    Log.e("token", token)
                }

                val request = chain.request().newBuilder()
                if(pref != null) request.addHeader("Authorization", token.toString())

                chain.proceed(request.build())
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }
}