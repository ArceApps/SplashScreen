package com.arceapps.splashscreen

import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
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
        private const val TIMER_ANIMATION: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // keep splash screen on-screen indefinitely.
        // keepSplashScreenIndefinitely()

        // if you want to use custom exit animation.
        useCustomExitAnimation()

        // keep splash screen on-screen for longer period.
        splashScreenWhenViewModel()
    }

    /**
     * Use customize exit animation for splash screen.
     */
    private fun useCustomExitAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val customAnimation = CustomAnimation()
            val animation = customAnimation.scaleXYAnimation(splashScreenView)

            val animatorSet = AnimatorSet()
            animatorSet.duration = TIMER_ANIMATION
            animatorSet.interpolator = AnticipateInterpolator()
            animatorSet.playTogether(animation)

            animatorSet.doOnEnd {
                splashScreenView.remove()
                toMainActivity()
            }
            animatorSet.start()
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
    private fun splashScreenWhenViewModel() {
        val content: View = findViewById(android.R.id.content)
        val model = ViewModelSplash()
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (model.isDataReady()) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        useCustomExitAnimation()
                        return true
                    } else return false
                }
            }
        )
    }

    private fun toMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}