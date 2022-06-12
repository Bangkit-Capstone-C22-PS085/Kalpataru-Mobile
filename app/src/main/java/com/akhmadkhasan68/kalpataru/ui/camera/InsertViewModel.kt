package com.akhmadkhasan68.kalpataru.ui.camera

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmadkhasan68.kalpataru.data.remote.response.CreateCartResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.TrashDetailResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.Trash
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _data = MutableLiveData<Trash>()
    val data : LiveData<Trash> = _data

    private val _isError  = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean> = _isError

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _isSuccess  = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    fun getDataPrediction(b64 : String){
        _isLoading.value = true
        client.getPredictionPrice(b64).enqueue(object: Callback<TrashDetailResponse> {
            override fun onResponse(
                call: Call<TrashDetailResponse>,
                response: Response<TrashDetailResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    Log.e("result", response.body()?.data.toString())
                    _data.value = response.body()?.data
                    _isError.value = false
                    _message.value = response.body()?.message!!
                }else{
                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")

                    _isError.value = true
                    _message.value = errorResponse.message!!
                }
            }

            override fun onFailure(call: Call<TrashDetailResponse>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun createCart(trashses_id : Int ?, quantity : Int, subtotal : String){
        _isLoading.value = true
        if (trashses_id != null) {
            client.createCart(trashses_id, quantity, subtotal).enqueue(object: Callback<CreateCartResponse> {
                override fun onResponse(
                    call: Call<CreateCartResponse>,
                    response: Response<CreateCartResponse>
                ) {
                    _isLoading.value = false
                    if(response.isSuccessful){
                        Log.e("result", response.body()?.data.toString())

                        _message.value = response.body()?.message!!
                    }else{
                        val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
                        Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")
                        _message.value = errorResponse.message!!
                    }

                    _isSuccess.value = response.isSuccessful
                }

                override fun onFailure(call: Call<CreateCartResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
    }
}