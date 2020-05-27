package com.ford.customsnackbar

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar

class BannerSnackBar(
    parent: ViewGroup,
    content: InfoSnackBarView
) : BaseTransientBottomBar<BannerSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        @JvmStatic
        fun make(view: View): BannerSnackBar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )
            val customView = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_info_banner, parent, false) as InfoSnackBarView
            return BannerSnackBar(
                parent,
                customView
            )
        }
    }
}