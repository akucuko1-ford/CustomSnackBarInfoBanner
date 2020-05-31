package com.fordx.widget

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientTopBar

class BannerSnackBar(
    parent: ViewGroup,
    content: InfoSnackBarView
) : BaseTransientTopBar<BannerSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
        getView().layoutParams = (getView().layoutParams as FrameLayout.LayoutParams).apply { gravity = Gravity.TOP }
    }

    @JvmOverloads
    fun setDismissAction(listener: View.OnClickListener? = null): BannerSnackBar {
        val contentLayout = this.view.getChildAt(0) as InfoSnackBarView
        val btn = contentLayout.getDismissView()
        btn.visibility = View.VISIBLE
        btn.setOnClickListener {
            listener?.onClick(it)
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
        @[JvmStatic JvmOverloads]
        fun make(view: View, text: String, messageType: InfoSnackBarView.MessageType? = null): BannerSnackBar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )
            val customView = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_info_banner, parent, false) as InfoSnackBarView
            customView.setText(text)
            messageType?.let { customView.setMessageType(it) }
            return BannerSnackBar(
                parent,
                customView
            ).apply {
                duration = LENGTH_INDEFINITE
            }
        }
    }
}