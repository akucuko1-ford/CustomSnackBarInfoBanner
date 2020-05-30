package com.ford.customsnackbar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
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

    private val headerLine: View
    private val icon: ImageView
    private val actionView: TextView
    private val dismissView: TextView
    private val contextView: TextView

    init {
        View.inflate(context, R.layout.custom_info_banner, this)
        headerLine = findViewById(R.id.header_line)
        icon = findViewById(R.id.banner_icon)
        actionView = findViewById(R.id.learn_more_button)
        dismissView = findViewById(R.id.cancel_button)
        contextView = findViewById(R.id.banner_text)
    }

    fun setMessageType(type: MessageType) {
        when (type) {
            MessageType.ERROR -> {
                headerLine.setBackgroundColor(Color.parseColor("#94000C"))
                icon.setImageDrawable(context.getDrawable(R.drawable.ic_error_message))
                actionView.setTextColor(Color.parseColor("#E41D2D"))
                dismissView.setTextColor(Color.parseColor("#E41D2D"))
            }
            MessageType.WARNING -> {
                headerLine.setBackgroundColor(Color.parseColor("#B14303"))
                icon.setImageDrawable(context.getDrawable(R.drawable.ic_warning_message))
                actionView.setTextColor(Color.parseColor("#E3661D"))
                dismissView.setTextColor(Color.parseColor("#E3661D"))
            }
            MessageType.INFO -> {
                headerLine.setBackgroundColor(Color.parseColor("#183553"))
                icon.setImageDrawable(context.getDrawable(R.drawable.ic_info_message))
                actionView.setTextColor(Color.parseColor("#225991"))
                dismissView.setTextColor(Color.parseColor("#225991"))
            }
        }
    }

    fun setText(text: String) {
        contextView.text = text
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

    enum class MessageType {
        ERROR,
        INFO,
        WARNING
    }
}