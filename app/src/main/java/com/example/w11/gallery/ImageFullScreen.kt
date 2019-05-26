package com.example.w11.gallery


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.w11.R
import kotlinx.android.synthetic.main.fullscreen_image.*


class ImageFullScreen : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    var position = -1
    lateinit var item: ArrayList<Int>
    private fun setupSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_image)
        item = intent.getIntegerArrayListExtra("Item")
        position = intent.getIntExtra("Pos", 0)
        fullscreenImageView.setImageResource(item[position])
        var sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        setListener(sharedPref.getBoolean("animations", false))
        setupSharedPreferences()

    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (key == "animations") {
            setListener(sharedPreferences.getBoolean("animations", false))
        }

    }

    private fun setListener(boolean: Boolean) {
        if (boolean) {
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
        } else
            fullscreenImageView.setOnTouchListener(object : SwipeListener2(this) {
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


    override fun onDestroy() {
        super.onDestroy()
        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

}

