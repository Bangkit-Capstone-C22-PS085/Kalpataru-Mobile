package com.akhmadkhasan68.kalpataru.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.history.HistoryViewModel
import com.akhmadkhasan68.kalpataru.ui.login.LoginViewModel
import com.akhmadkhasan68.kalpataru.ui.main.MainViewModel
import com.akhmadkhasan68.kalpataru.ui.profile.ProfileViewModel
import com.akhmadkhasan68.kalpataru.ui.register.RegisterViewModel
import com.akhmadkhasan68.kalpataru.ui.splash.SplashViewModel


class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(pref) as T
            }modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}