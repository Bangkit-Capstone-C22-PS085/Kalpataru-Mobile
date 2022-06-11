package com.akhmadkhasan68.kalpataru.ui.history

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.LoginResponse
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserModel
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _data = MutableLiveData<String>()
    val data : LiveData<String> = _data

    fun getData(){
        _isLoading.value = true
//        client.login(username, password).enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(
//                call: Call<LoginResponse>,
//                response: Response<LoginResponse>
//            ) {
//                _isLoading.value = false
//
//                if(response.isSuccessful){
//                    Log.e(ContentValues.TAG, response.body().toString())
//
//                    val name = response.body()?.user?.member?.name
//                    val username = response.body()?.user?.username
//                    val email = response.body()?.user?.email
//                    val type = response.body()?.user?.type
//                    val userId = response.body()?.user?.id.toString()
//                    val token = response.body()?.token?.accessToken
//
//                    val userData = UserModel(
//                        userId!!,
//                        username!!,
//                        email!!,
//                        type!!,
//                        name!!,
//                        token!!,
//                        true
//                    )
//
//                    viewModelScope.launch {
//                        pref.login(userData)
//                    }
//                }else {
//                    val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
//
//                    _errorMessage.value = errorResponse.message!!
//                    Log.e(ContentValues.TAG, "onFailure: ${errorResponse.message}")
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
    }
}