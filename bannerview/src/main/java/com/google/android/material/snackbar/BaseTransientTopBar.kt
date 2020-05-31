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
            startSlideInAnimation()
        }
    }

    override fun onViewHidden(event: Int) {
        if (view.visibility == View.VISIBLE && event != BaseCallback.DISMISS_EVENT_MANUAL) {
            startSlideOutAnimation(event)
        } else {
            super.onViewHidden(event)
        }
    }

    private fun startSlideInAnimation() {
        val translationYBottom = view.height * -1f
        view.translationY = translationYBottom
        ValueAnimator().apply {
            setIntValues(translationYBottom.toInt(), 0)
            interpolator = FastOutSlowInInterpolator()
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    shouldAnimate = false
                    contentViewCallback.animateContentIn(70, 180)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    onViewShown()
                }
            })
            addUpdateListener {
                view.translationY = (it.animatedValue as Int).toFloat()
            }
            start()
        }
    }

    private fun startSlideOutAnimation(event: Int) {
        val translationYBottom = view.height * -1f
        view.translationY = translationYBottom
        ValueAnimator().apply {
            setFloatValues(0f, translationYBottom)
            interpolator = FastOutSlowInInterpolator()
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    contentViewCallback.animateContentOut(0, ANIMATION_FADE_DURATION)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    removeView(event)
                }
            })
            addUpdateListener {
                view.translationY = it.animatedValue as Float
            }
            start()
        }
    }

    private fun removeView(event: Int) {
        super.onViewHidden(event)
    }
}