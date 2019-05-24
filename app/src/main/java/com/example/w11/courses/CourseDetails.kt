package com.example.w11.courses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.w11.R
import kotlinx.android.synthetic.main.course_details.*

class CourseDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_details)
        name.text = intent.getStringExtra("name")
        lecturer.text = "Prowadzacy:\n" + intent.getStringExtra("lecturer")
        semester.text = "Semestr: " + intent.getIntExtra("semester", 1).toString()
        ects.text = "ECTS: " + intent.getIntExtra("ects", 6).toString()
        description.text = "Opis kursu:\n" + intent.getStringExtra("description")
    }

}