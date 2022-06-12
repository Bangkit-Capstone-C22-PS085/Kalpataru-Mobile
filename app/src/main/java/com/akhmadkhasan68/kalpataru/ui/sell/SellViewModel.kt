package com.akhmadkhasan68.kalpataru.ui.sell

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmadkhasan68.kalpataru.data.remote.response.CartResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.CreateTransactionResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.TransactionResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataCart
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean> = _isError

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _data = MutableLiveData<List<DataCart>>()
    val data : LiveData<List<DataCart>> = _data

    fun getData(){
        _isLoading.value = true
        client.getCart().enqueue(object: Callback<CartResponse> {
            override fun onResponse(
                call: Call<CartResponse>,
                response: Response<CartResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _data.value = response.body()?.data
                }else{
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun createTransaction(){
        _isLoading.value = true
        client.createTransaction().enqueue(object: Callback<CreateTransactionResponse>{
            override fun onResponse(
                call: Call<CreateTransactionResponse>,
                response: Response<CreateTransactionResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _isError.value = false
                    _message.value = response.body()?.message!!
                }else{
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")

                    _isError.value = true
                    _message.value = errorResponse.message!!
                }
            }

            override fun onFailure(call: Call<CreateTransactionResponse>, t: Throwable) {
                _isLoading.value = false
                _isError.value = false
                _message.value = t.message.toString()

                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}