package com.akhmadkhasan68.kalpataru.ui.history

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akhmadkhasan68.kalpataru.data.remote.response.BuyResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserModel
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryDetailViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun completeTransaction(id: Int){
        _isLoading.value = true
        client.completeTransaction(id).enqueue(object : Callback<BuyResponse> {
            override fun onResponse(
                call: Call<BuyResponse>,
                response: Response<BuyResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    Log.e(ContentValues.TAG, response.body().toString())
                    _message.value = response.body()?.message!!
                }else {
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")

                    _message.value = errorResponse.message!!
                }

                _isSuccess.value = response.isSuccessful
            }

            override fun onFailure(call: Call<BuyResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}