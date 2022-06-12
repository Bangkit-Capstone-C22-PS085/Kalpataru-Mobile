package com.akhmadkhasan68.kalpataru.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.MeResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.TransactionResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<User>()
    val userDetail : LiveData<User> = _userData

    private val _dataTransaction = MutableLiveData<List<DataTransactions>>()
    val dataTransaction : LiveData<List<DataTransactions>> = _dataTransaction

    fun getUser() {
        _isLoading.value = true
        client.me().enqueue(object : Callback<MeResponse> {
            override fun onResponse(
                call: Call<MeResponse>,
                response: Response<MeResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    Log.e(ContentValues.TAG, response.body().toString())

                    _userData.value = User(
                        response.body()?.detail?.createdAt,
                        response.body()?.detail?.password,
                        response.body()?.detail?.member,
                        response.body()?.detail?.id,
                        response.body()?.detail?.type,
                        response.body()?.detail?.email,
                        response.body()?.detail?.operator,
                        response.body()?.detail?.username,
                        response.body()?.detail?.updatedAt,
                    )
                }else {
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")
                }
            }

            override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDataTransaction() {
        _isLoading.value = true
        client.getDiscoverTransactions().enqueue(object : Callback<TransactionResponse> {
            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    Log.e(ContentValues.TAG, response.body().toString())

                    _dataTransaction.value = response.body()?.data
                }else {
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}