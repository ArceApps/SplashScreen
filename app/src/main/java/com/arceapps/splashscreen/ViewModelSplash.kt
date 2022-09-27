package com.arceapps.splashscreen

import android.os.SystemClock

/**
 * Created by ArceApps on 24/09/2022.
 * Project name: SplashScreen.
 */
class ViewModelSplash {
    companion object {
        const val WORK_DURATION = 3000L
    }
    private val initTime = SystemClock.uptimeMillis()
    fun isDataReady() = SystemClock.uptimeMillis() - initTime > WORK_DURATION
}