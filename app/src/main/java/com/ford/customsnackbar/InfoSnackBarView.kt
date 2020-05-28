package com.ford.customsnackbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback

class InfoSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val actionView: TextView
    private val dismissView: TextView

    init {
        val view = View.inflate(context, R.layout.custom_info_banner, this)
        actionView = view.findViewById(R.id.learn_more_button)
        dismissView = view.findViewById(R.id.cancel_button)
    }

    fun getActionView(): TextView = actionView

    fun getDismissView(): TextView = dismissView

    override fun animateContentOut(delay: Int, duration: Int) {
//        TODO("Not yet implemented")
    }

    override fun animateContentIn(delay: Int, duration: Int) {
//        TODO("Not yet implemented")
    }
}