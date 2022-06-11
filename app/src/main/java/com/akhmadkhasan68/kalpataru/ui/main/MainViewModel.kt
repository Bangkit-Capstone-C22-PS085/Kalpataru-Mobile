package com.akhmadkhasan68.kalpataru.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akhmadkhasan68.kalpataru.model.UserModel
import com.akhmadkhasan68.kalpataru.model.UserPreference

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}