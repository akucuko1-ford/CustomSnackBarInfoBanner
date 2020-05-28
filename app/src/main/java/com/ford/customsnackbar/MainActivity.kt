package com.ford.customsnackbar

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val snackBar = BannerSnackBar.make(main_view, LENGTH_INDEFINITE)
        val layoutParams = snackBar.view.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = Gravity.TOP
        snackBar.view.layoutParams = layoutParams
        snackBar.setDismissAction(View.OnClickListener {
            println("action worked")
        })
        snackBar.setMoreAction(View.OnClickListener {
            println("more action has been clicked")
            startActivity(Intent(this, LearnMoreActivity::class.java))
        })
        snackBar.show()
    }
}
