package com.example.w11.courses

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.w11.R
import kotlinx.android.synthetic.main.courses.*
import kotlinx.android.synthetic.main.courses.view.*

class CoursesActivity : AppCompatActivity() {
    lateinit var courseList : ArrayList<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courses)

        courseList = ArrayList<Course>()
        courseList.add(Course(R.drawable.math, "Analiza matematyczna", 1, 8, "Pawel Krupski", "Fajny przedmiocik dla kogoś o stalowych nerwach."))
        courseList.add(Course(R.drawable.math, "Algebra liniowa", 1, 7, "Jacek Cichon", "Dużo abstrakcji, dużo niezrozumiałych znaczków, ETrapez niezbędny."))
        courseList.add(Course(R.drawable.inf_icon, "Wstep do informatyki i programowania", 1, 7, "Przemyslaw Kobylanski", "Nauczysz sie troche C, ale ogolnie do sie nie ucz bo Ada lepsza."))
        courseList.add(Course(R.drawable.math, "Logika i struktury formalne", 1, 8, "Szymon Zeberski", "Potrzebne rzeczy, solidny prowadzacy, wymagajace kolokwium."))
        val myAdapter = MyCourseAdapter(this, courseList)
        courses.adapter = myAdapter

        courses.setOnItemClickListener {_, _, index, _ ->
            var course = courseList[index]
            val intent = Intent(this, CourseDetails::class.java)
            intent.putExtra("picture", course.picture);
            intent.putExtra("name", course.name)
            intent.putExtra("semester", course.name)
            intent.putExtra("ects", course.ects)
            intent.putExtra("lecturer", course.teacher)
            intent.putExtra("description", course.description)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("taskList", courseList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        courseList = savedInstanceState?.getSerializable("taskList") as ArrayList<Course>
        val myAdapter = MyCourseAdapter(this, courseList)
        courses.adapter = myAdapter
    }

}