package com.afs.mobile.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afs.mobile.common.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _splashScreenFinished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val splashScreenFinished: LiveData<Boolean> = _splashScreenFinished

    fun delaySplashScreen() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            _splashScreenFinished.value = true
        }
    }
}