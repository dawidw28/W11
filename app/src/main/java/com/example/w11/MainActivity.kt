package com.example.w11

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.w11.courses.CoursesActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Screen.setBackgroundColor(Color.WHITE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.b1 -> {
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            }
            R.id.b2 ->{
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)

            }
            R.id.b3 ->{
                val intent = Intent(this, ThirdActivity::class.java)
               startActivity(intent)
            }
            R.id.b4 -> {
                val intent = Intent(this, CoursesActivity::class.java)
                startActivity(intent)
            }
            R.id.b7 -> {
                val intent = Intent(this, ContactActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
