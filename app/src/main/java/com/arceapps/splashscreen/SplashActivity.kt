package com.arceapps.splashscreen

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

/**
 * Created by ArceApps on 13/08/2022.
 * Project name: SplashScreen.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashScreen : SplashScreen
    companion object {
        private const val TIMER_ANIMATION: Long = 1500
        private const val TIMER_DELAY: Long = 5000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // keep splash screen on-screen indefinitely.
        // keepSplashScreenIndefinitely()

        // if you want to use custom exit animation.
        useCustomExitAnimation()

        // keep splash screen on-screen for longer period.
        // keepSplashScreenFor5Seconds()
    }

    /**
     * Use customize exit animation for splash screen.
     */
    private fun useCustomExitAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat(),
            )
            slideBack.interpolator = DecelerateInterpolator()
            slideBack.duration = TIMER_ANIMATION
            slideBack.doOnEnd {
                splashScreenView.remove()
                toMainActivity()
            }
            slideBack.start()
        }
    }

    /**
     * Keep splash screen on-screen indefinitely. This is useful if you're using a custom Activity
     * for routing.
     */
    private fun keepSplashScreenIndefinitely() {
        splashScreen.setKeepOnScreenCondition { true }
    }

    /**
     * Keep splash screen on-screen for longer period. This is useful if you need to load data when
     * splash screen is appearing.
     */
    private fun keepSplashScreenFor5Seconds() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            Thread.sleep(TIMER_DELAY)
            splashScreenView.remove()
            toMainActivity()
        }
    }

    private fun toMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}