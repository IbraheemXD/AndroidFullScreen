package com.ebraheemxd.fullscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ebraheemxd.fullscreen.utils.SystemUiHelper
import com.ebraheemxd.fullscreen.utils.SystemUiHelper.Companion.LEVEL_IMMERSIVE

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = SystemUiHelper(this, LEVEL_IMMERSIVE, 0)

        val textView = findViewById<TextView>(R.id.textView)
        textView.setOnClickListener {
            if (helper.isShowing) {
                helper.hide()
            } else {
                helper.show()
            }
        }
    }
}