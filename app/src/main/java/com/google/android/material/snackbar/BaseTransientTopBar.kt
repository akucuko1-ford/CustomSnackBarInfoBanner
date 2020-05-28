package com.google.android.material.snackbar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

abstract class BaseTransientTopBar<T : BaseTransientTopBar<T>>(
    parent: ViewGroup,
    content: View,
    private val contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<T>(parent, content, contentViewCallback) {

    private var shouldAnimate = true

    override fun shouldAnimate(): Boolean = shouldAnimate

    override fun animateViewIn() {
        view.post {
            view.visibility = View.VISIBLE
            startSlideAnimation()
        }
    }

    private fun startSlideAnimation() {
        val translationYBottom = view.height * -1f
        view.translationY = translationYBottom
        ValueAnimator().apply {
            setIntValues(translationYBottom.toInt(), 0)
            interpolator = FastOutSlowInInterpolator()
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    contentViewCallback.animateContentIn(70, 180)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    onViewShown()
                    shouldAnimate = false
                }
            })
            addUpdateListener {
                view.translationY = (it.animatedValue as Int).toFloat()
            }
            start()
        }
    }
}