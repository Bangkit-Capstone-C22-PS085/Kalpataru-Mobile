package com.akhmadkhasan68.kalpataru.ui.profile

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.akhmadkhasan68.kalpataru.data.remote.response.ErrorResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.MeResponse
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.akhmadkhasan68.kalpataru.data.remote.retrofit.GithubApiConfig
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val pref: UserPreference) : ViewModel() {
    private val client = GithubApiConfig.getGithubApiServices(pref)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<User>()
    val userDetail : LiveData<User> = _userData

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

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

    fun logout(){
        viewModelScope.launch {
            pref.logout()
        }
    }
}