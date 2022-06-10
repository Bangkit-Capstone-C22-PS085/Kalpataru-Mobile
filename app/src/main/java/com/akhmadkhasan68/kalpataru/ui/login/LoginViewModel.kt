package com.akhmadkhasan68.kalpataru.ui.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserModel
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun login(email: String, password: String){
        _isLoading.value = true

//        client.login(email, password).enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(
//                call: Call<LoginResponse>,
//                response: Response<LoginResponse>
//            ) {
//                _isLoading.value = false
//                if(response.isSuccessful){
//                    Log.e(ContentValues.TAG, response.body().toString())
//                    val name = response.body()?.loginResult?.name
//                    val userId = response.body()?.loginResult?.userId
//                    val token = response.body()?.loginResult?.token
//
//                    val userData = UserModel(
//                        userId!!,
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