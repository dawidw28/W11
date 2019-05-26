package com.example.w11.gallery


import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView


open class SwipeListener(ctx: Context, fullscreenImageView: ImageView) : View.OnTouchListener {

    var MyView: ImageView

    val xIni: Int
    val yIni: Int
    private var mDownX: Float = 0.toFloat()
    private var mDownY: Float = 0.toFloat()
    var mPointerId = 0

    var screenW: Int

    init {
        MyView = fullscreenImageView
        xIni = MyView.left
        yIni = MyView.top
        var metrics = DisplayMetrics()
        (ctx as Activity).windowManager.defaultDisplay.getMetrics(metrics)
        screenW = metrics.widthPixels
    }

    override fun onTouch(v: View, e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {

                MyView.parent.requestDisallowInterceptTouchEvent(true)
                mPointerId = e.getPointerId(0)
                mDownX = e.getX(mPointerId)
                mDownY = e.getY(mPointerId)

                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val pointerIndex = e.findPointerIndex(mPointerId)
                if (pointerIndex < 0) return false

                val dx = e.getX(pointerIndex) - mDownX
                val dy = e.getY(pointerIndex) - mDownY

                val newX = MyView.x + dx
                val newY = MyView.y + dy

                MyView.x = newX
                MyView.y = newY

                var dragDistanceX = newX - xIni
                var swipeProgress = Math.min(
                    Math.max(
                        dragDistanceX / screenW, -1f
                    ), 1f
                )


                if (0.3 < 1f) {
                    var alpha = 1 - Math.min(Math.abs(swipeProgress * 1), 1f)
                    MyView.alpha = alpha
                }

                return true
            }
            MotionEvent.ACTION_UP -> {
                v.parent.requestDisallowInterceptTouchEvent(false)
                checkViewPosition()
                return true
            }
        }
        return false
    }

    private fun checkViewPosition() {
        val viewCenterHorizontal = MyView.x + MyView.width / 2
        val parentFirstThird = screenW / 5
        val parentLastThird = parentFirstThird * 4

        if (viewCenterHorizontal < parentFirstThird) {
            swipeViewToLeft(300 / 2)
        } else if (viewCenterHorizontal > parentLastThird) {
            swipeViewToRight(300 / 2)
        } else {
            resetViewPosition()
        }
    }

    private fun resetViewPosition() {
        MyView.animate()
            .x(xIni.toFloat())
            .y(yIni.toFloat())
            .alpha(1f).duration = 250
    }

    private fun resetView(heh: Boolean) {
        MyView.x = xIni.toFloat()
        MyView.y = yIni.toFloat()
        if (heh) onSwipeRight() else onSwipeLeft()
        MyView.animate().alpha(1f).duration = 100
    }

    private fun swipeViewToLeft(duration: Int) {
        MyView.animate().cancel()
        MyView.animate()
            .x(-screenW + MyView.x)
            .alpha(0f)
            .setDuration(250).withEndAction {
                resetView(false)
            }
    }

    private fun swipeViewToRight(duration: Int) {
        MyView.animate().cancel()
        MyView.animate()
            .x(screenW + MyView.x)
            .alpha(0f)
            .setDuration(250).withEndAction {
                resetView(true)
            }

    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}


}