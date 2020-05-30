package com.ford.customsnackbar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback

class InfoSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val icon: ImageView
    private val actionView: TextView
    private val dismissView: TextView
    private val contextView: TextView

    init {
        View.inflate(context, R.layout.custom_info_banner, this)
        icon = findViewById(R.id.banner_icon)
        actionView = findViewById(R.id.learn_more_button)
        dismissView = findViewById(R.id.cancel_button)
        contextView = findViewById(R.id.banner_text)
    }

    fun getActionView(): TextView = actionView

    fun getDismissView(): TextView = dismissView

    override fun animateContentOut(delay: Int, duration: Int) {
//        TODO("Not yet implemented")
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val viewAlpha = ObjectAnimator.ofFloat(contextView, View.ALPHA, 0f, 1f)
        AnimatorSet().apply {
            this.duration = 500
            playTogether(viewAlpha)
            start()
        }
        val scaleX = ObjectAnimator.ofFloat(icon, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(icon, View.SCALE_Y, 0f, 1f)
        AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            this.duration = 500
            playTogether(scaleX, scaleY)
            start()
        }
    }
}