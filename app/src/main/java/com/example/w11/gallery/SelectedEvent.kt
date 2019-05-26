package com.example.w11.gallery


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.w11.R
import kotlinx.android.synthetic.main.selected_event.*


class SelectedEvent : AppCompatActivity() {


    var state = false

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.selected_event)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val event = intent.getSerializableExtra("Item") as Event

        titleS.text = event.title
        desc.text = event.description

        rcvb.layoutManager = GridLayoutManager(this, 3)
        rcvb.adapter = MyAdapter2(event.imagesID, this)

        desc.movementMethod = ScrollingMovementMethod()
        val params = desc.layoutParams as ViewGroup.LayoutParams
        params.height = 300
        desc.layoutParams = params

        titleS.setOnClickListener {
            if (!state) {
                desc.visibility = View.VISIBLE
                descShowBut.setImageResource(R.drawable.arrow_up)
            } else {
                desc.visibility = View.GONE
                descShowBut.setImageResource(R.drawable.arrow_down)
            }

            state = !state
        }
    }
}

