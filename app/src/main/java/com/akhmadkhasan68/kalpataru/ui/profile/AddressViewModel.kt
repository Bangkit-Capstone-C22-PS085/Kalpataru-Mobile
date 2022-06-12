package com.akhmadkhasan68.kalpataru.ui.profile

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.MeResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel (private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun updateAddress(long: Float, lat: Float, address: String){
        _isLoading.value = true

        client.updateAddress(long, lat, address).enqueue(object : Callback<MeResponse> {
            override fun onResponse(
                call: Call<MeResponse>,
                response: Response<MeResponse>
            ) {
                _isLoading.value = false

                if(response.isSuccessful){
                    Log.e(ContentValues.TAG, response.body().toString())
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
}