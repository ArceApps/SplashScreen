package com.arceapps.splashscreen

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Path
import android.view.View
import androidx.core.splashscreen.SplashScreenViewProvider

/**
 * Created by ArceApps on 25/09/2022.
 * Project name: SplashScreen.
 */
class CustomScreenAnimator {

    @SuppressLint("Recycle")
    public fun slideUpAnimation(splashScreenView: SplashScreenViewProvider): ObjectAnimator {
        return ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.TRANSLATION_Y,
            0f,
            -splashScreenView.view.height.toFloat(),
        )
    }

    @SuppressLint("Recycle")
    public fun slideLeftAnimation(splashScreenView: SplashScreenViewProvider): ObjectAnimator {
        return ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.TRANSLATION_X,
            0f,
            -splashScreenView.view.width.toFloat()
        )
    }

    @SuppressLint("Recycle")
    public fun scaleXAnimation(splashScreenView: SplashScreenViewProvider): ObjectAnimator {
        return ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.SCALE_X,
            1.0f,
            0f
        )
    }

    @SuppressLint("Recycle")
    public fun scaleXYAnimation(splashScreenView: SplashScreenViewProvider): ObjectAnimator {
        val path = Path()
        path.moveTo(1.0f, 1.0f)
        path.lineTo(0f, 0f)

        return ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.SCALE_X,
            View.SCALE_Y,
            path
        )
    }

    @SuppressLint("Recycle")
    public fun alphaAnimation(splashScreenView: SplashScreenViewProvider): ObjectAnimator {
        return ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.ALPHA,
            1f,
            0f
        )
    }
}