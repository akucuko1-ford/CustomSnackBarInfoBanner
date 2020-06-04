package com.fordx.widget

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class FdsBannerSnackBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val icon: ImageView
    private val background: View
    private val contextView: TextView
    private val actionView: ImageView

    init {
        View.inflate(context, R.layout.fds_info_banner, this)
        icon = findViewById(R.id.fds_banner_icon)
        background = findViewById(R.id.view_background)
        contextView = findViewById(R.id.fds_banner_text)
        actionView = findViewById(R.id.banner_action_icon)
    }

    fun getActionView(): ImageView = actionView

    override fun animateContentOut(delay: Int, duration: Int) {
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        background.translationY += background.height * -1f
        icon.translationY += background.height * -1f
        contextView.translationY += background.height * -1f
        actionView.translationY += background.height * -1f
    }

    override fun onContextAnimationEnd() {
        ValueAnimator().apply {
            setIntValues(background.height * -1, 0)
            interpolator = AccelerateDecelerateInterpolator()
            duration = 500
            addUpdateListener {
                background.translationY = (it.animatedValue as Int).toFloat()
                icon.translationY = (it.animatedValue as Int).toFloat()
                contextView.translationY = (it.animatedValue as Int).toFloat()
                actionView.translationY = (it.animatedValue as Int).toFloat()
            }
            start()
        }
    }
}