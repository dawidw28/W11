package com.example.w11.gallery


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import com.example.w11.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.gallery_main.*
import android.graphics.drawable.BitmapDrawable as BitmapDrawable1

class MainGallery : AppCompatActivity() {

    fun initData(): Array<Event>? {
        var json: String? = null
        val input = assets.open("src.json")
        json = input.bufferedReader().use { it.readText() }
        val gson = Gson()
        val events = gson.fromJson(json, Array<Event>::class.java)


        var sharedPref = this.getPreferences(Context.MODE_PRIVATE)


        events.forEach {
            it.images!!.forEach { c -> it.imagesID.add(resources.getIdentifier(c, "drawable", packageName)) }
            it.avgC = getAverageColorRGB(it.imagesID[0])
            it.fav = sharedPref.getBoolean("${it.title}", false)
        }
        events.sortWith(compareByDescending<Event> { it.fav }.thenByDescending { it.date })
        return events
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_main)
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val data = initData()
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(this, 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 3 == 0)
                            2
                        else
                            1
                    }
                }
            }
        } else recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(data!!, this)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
        }
    }

    fun getAverageColorRGB(res: Int): Int {
        val bitmapp = resources.getDrawable(res) as BitmapDrawable1
        val bitmap = bitmapp.bitmap
        val width = bitmap.width
        val height = bitmap.height
        val heightO = height / 4
        val heightN = height * 0.75
        val widthO = width / 4
        val widthN = width * 0.75
        var size = 0//(2 * widthO * (height) + 2 * heightO * (width - 2 * widthO)) / 30
        var pixelColor: Int
        var r: Int
        var g: Int
        var b: Int
        b = 0
        g = b
        r = g
        for (x in 0 until width step 10) {
            for (y in 0 until heightO step 10) {
                pixelColor = bitmap.getPixel(x, y)
                if (pixelColor == 0) {
                    size--
                    continue
                }
                size++
                r += Color.red(pixelColor)
                g += Color.green(pixelColor)
                b += Color.blue(pixelColor)
            }
            for (y in heightO until height step 10) {
                pixelColor = bitmap.getPixel(x, y)
                if (pixelColor == 0) {
                    size--
                    continue
                }
                size++
                r += Color.red(pixelColor)
                g += Color.green(pixelColor)
                b += Color.blue(pixelColor)
            }

        }

        for (x in heightO until heightN.toInt() step 10) {
            for (y in 0 until widthO step 10) {
                pixelColor = bitmap.getPixel(y, x)
                if (pixelColor == 0) {
                    size--
                    continue
                }
                size++
                r += Color.red(pixelColor)
                g += Color.green(pixelColor)
                b += Color.blue(pixelColor)
            }
            for (y in widthN.toInt() until width step 10) {
                pixelColor = bitmap.getPixel(y, x)
                if (pixelColor == 0) {
                    size--
                    continue
                }
                size++
                r += Color.red(pixelColor)
                g += Color.green(pixelColor)
                b += Color.blue(pixelColor)
            }

        }

        r /= size
        g /= size
        b /= size
        return Color.rgb(r, g, b)
    }
}

