package com.ford.customsnackbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fordx.widget.FdsBannerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        FdsBannerView.make(main_view).setActionView().show()
    }
}
