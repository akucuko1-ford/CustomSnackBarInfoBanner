package com.google.android.material.snackbar

import android.view.View
import android.view.ViewGroup

abstract class BaseTransientTopBar<T: BaseTransientTopBar<T>>(
    parent: ViewGroup,
    content: View,
    contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<T>(parent, content, contentViewCallback) {

    override fun shouldAnimate(): Boolean = false
}

//841