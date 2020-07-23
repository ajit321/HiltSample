package com.hilt.hiltsample

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.hilt.hiltsample.utils.isNight
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class HiltAppSample : Application() {
    override fun onCreate() {
        super.onCreate()
        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}