package com.example.w11.gallery

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.w11.R
import kotlinx.android.synthetic.main.resizable_image.*

class ResizableImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resizable_image)
        var item = intent.getIntExtra("eee", 0)
        fullscreenImageView2.setImageResource(item)
    }
}