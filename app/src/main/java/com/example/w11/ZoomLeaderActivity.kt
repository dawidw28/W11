package com.example.w11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ZoomLeaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.zoom_leader)

        val name = findViewById<View>(R.id.nameView) as TextView
        val picture = findViewById<View>(R.id.image) as ImageView
        val day1 = findViewById<View>(R.id.dayView1) as TextView
        val consultationsTime1 = findViewById<View>(R.id.consultationsView1) as TextView
        val day2 = findViewById<View>(R.id.dayView2) as TextView
        val consultationsTime2 = findViewById<View>(R.id.consultationsView2) as TextView
        val room = findViewById<View>(R.id.roomView) as TextView
        val degree = findViewById<View>(R.id.degreeView) as TextView
        val phone = findViewById<View>(R.id.phoneView) as TextView
        val email = findViewById<View>(R.id.emailView) as TextView

        name.text = intent.getStringExtra("name")
        picture.setImageResource(intent.getIntExtra("picture", 0))
        day1.text = intent.getStringExtra("day1")
        room.text = intent.getStringExtra("room")
        consultationsTime1.text = intent.getStringExtra("time1")
        day2.text = intent.getStringExtra("day2")
        consultationsTime2.text = intent.getStringExtra("time2")
        degree.text = intent.getStringExtra("degree")
        phone.text = intent.getStringExtra("phone")
        email.text = intent.getStringExtra("email")
        }
    }

