package com.example.w11.gallery


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.w11.R
import kotlinx.android.synthetic.main.fullscreen_image.*

class ImageFullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_image)
        var item = intent.getIntegerArrayListExtra("Item")
        var position = intent.getIntExtra("Pos", 0)
        fullscreenImageView.setImageResource(item.get(position))


        fullscreenImageView.setOnTouchListener(object : SwipeListener(this, fullscreenImageView) {

            override fun onSwipeLeft() {
                var newPos = position + 1
                if (newPos == item.size) newPos = 0
                fullscreenImageView.setImageResource(item.get(newPos))
                position = newPos
                Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeRight() {
                var newPos = position - 1
                if (newPos < 0) newPos = item.size - 1
                fullscreenImageView.setImageResource(item.get(newPos))
                position = newPos
                Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

