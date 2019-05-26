package com.example.w11.gallery


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.method.ScrollingMovementMethod
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.w11.R
import kotlinx.android.synthetic.main.selected_event.*


class SelectedEvent : AppCompatActivity() {


    var state = false

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(com.example.w11.R.layout.selected_event)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val event = intent.getSerializableExtra("Item") as Event

        titleS.text = event.title
        desc.text = event.description

        var mGridLayoutManager1 = GridLayoutManager(this, 1)
        var mGridLayoutManager2 = GridLayoutManager(this, 2)
        var mGridLayoutManager3 = GridLayoutManager(this, 3)

        var mCurrentLayoutManager = mGridLayoutManager3

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


        val mScaleGestureDetector =
            ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    if (detector.currentSpan > 250 && detector.timeDelta > 250) {
                        if (detector.currentSpan - detector.previousSpan < -1) {
                            if (mCurrentLayoutManager === mGridLayoutManager1) {
                                mCurrentLayoutManager = mGridLayoutManager2
                                rcvb.layoutManager = mGridLayoutManager2
                                return true
                            } else if (mCurrentLayoutManager === mGridLayoutManager2) {
                                mCurrentLayoutManager = mGridLayoutManager3
                                rcvb.layoutManager = mGridLayoutManager3
                                return true
                            }
                        } else if (detector.currentSpan - detector.previousSpan > 1) {
                            if (mCurrentLayoutManager === mGridLayoutManager3) {
                                mCurrentLayoutManager = mGridLayoutManager2
                                rcvb.layoutManager = mGridLayoutManager2
                                return true
                            } else if (mCurrentLayoutManager === mGridLayoutManager2) {
                                mCurrentLayoutManager = mGridLayoutManager1
                                rcvb.layoutManager = mGridLayoutManager1
                                return true
                            }
                        }
                    }
                    return false
                }
            })


        rcvb.setOnTouchListener(View.OnTouchListener { v, event ->
            mScaleGestureDetector.onTouchEvent(event)
            false
        })

    }
}

