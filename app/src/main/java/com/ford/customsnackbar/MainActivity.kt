package com.ford.customsnackbar

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fordx.widget.BannerSnackBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        BannerSnackBar.make(main_view, getString(R.string.banner_string)).apply {
            setDismissAction()
            setMoreAction(View.OnClickListener {
                println("more action has been clicked")
                startActivity(Intent(this@MainActivity, LearnMoreActivity::class.java))
            })
        }.show()
    }
}
