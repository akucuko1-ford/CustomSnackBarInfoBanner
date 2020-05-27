package com.ford.customsnackbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback

class InfoSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    init {
        View.inflate(context, R.layout.custom_info_banner, this)
    }

    override fun animateContentOut(delay: Int, duration: Int) {
//        TODO("Not yet implemented")
    }

    override fun animateContentIn(delay: Int, duration: Int) {
//        TODO("Not yet implemented")
    }
}