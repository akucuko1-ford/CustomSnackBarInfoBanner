package com.fordx.widget

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.snackbar.BaseTransientTopBar

class FdsBannerView(
    parent: ViewGroup,
    content: FdsBannerSnackBar
) : BaseTransientTopBar<BannerSnackBar>(parent, content, content) {

    init {
        getView().setPadding(0, 0, 0, 0)
        getView().layoutParams = (getView().layoutParams as FrameLayout.LayoutParams).apply { gravity = Gravity.TOP }
    }

    fun setActionView(): FdsBannerView {
        val contentLayout = this.view.getChildAt(0) as FdsBannerSnackBar
        val btn = contentLayout.getActionView()
        btn.visibility = View.VISIBLE
        btn.setOnClickListener {
            dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION)
        }
        return this
    }

    companion object {
        @[JvmStatic]
        fun make(view: View): FdsBannerView {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )
            val customView = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_fds_banner, parent, false) as FdsBannerSnackBar
            return FdsBannerView(
                parent,
                customView
            ).apply {
                duration = LENGTH_INDEFINITE
            }
        }
    }
}