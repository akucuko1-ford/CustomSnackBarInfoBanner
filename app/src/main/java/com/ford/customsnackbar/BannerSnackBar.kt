package com.ford.customsnackbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientTopBar

class BannerSnackBar(
    parent: ViewGroup,
    content: InfoSnackBarView
) : BaseTransientTopBar<BannerSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    fun setDismissAction(listener: View.OnClickListener): BannerSnackBar {
        val contentLayout = this.view.getChildAt(0) as InfoSnackBarView
        val btn = contentLayout.getDismissView()
        btn.visibility = View.VISIBLE
        btn.setOnClickListener {
            listener.onClick(it)
            dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION)
        }
        return this
    }

    fun setMoreAction(listener: View.OnClickListener): BannerSnackBar {
        val contentLayout = this.view.getChildAt(0) as InfoSnackBarView
        val btn = contentLayout.getActionView()
        btn.visibility = View.VISIBLE
        btn.setOnClickListener {
            listener.onClick(it)
            dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION)
        }
        return this
    }

    companion object {
        @JvmStatic
        fun make(view: View, @Duration duration: Int): BannerSnackBar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )
            val customView = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_info_banner, parent, false) as InfoSnackBarView
            return BannerSnackBar(
                parent,
                customView
            ).apply {
                this.duration = duration
            }
        }
    }
}