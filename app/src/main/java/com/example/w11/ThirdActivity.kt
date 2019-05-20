package com.example.w11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class ThirdActivity : AppCompatActivity() {

    var leaders = ArrayList<Leader>()

    var choice = 0
    var name = ""
    var picture = 0
    var phone = ""
    var email =""
    var consultationsTime =""
    private var consultationsDay =""
    var degree = ""
    var room = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_leaders)

        val listView = findViewById<ListView>(R.id.list_of_leaders)

        leaders = arrayListOf(
            Leader(R.drawable.macyna, "Wojciech Macyna", "starszy wykładowca", "wojciech.macyna@pwr.edu.pl", "71 320 3048", "210/D-1", "13:15", "Sroda"),
            Leader(R.drawable.blaskiewicz, "Przemek Błaśkiewicz", "adiunkt", "przemyslaw.blaskiewicz@pwr.edu.pl", "71 320 2326", "216/D-1", "10:00", "Sroda"),
            Leader(R.drawable.krupski, "Paweł Krupski", "profesor", "pawel.krupski@pwr.edu.pl", "71 320 3362", "217/D-1", "09:00", "Czwartek")
        )


        listView.adapter = MyLeaderAdapter(this, R.layout.one_leader, leaders)

        listView.setOnItemClickListener { _, _, position, _ ->
            choice = position
            name = leaders[position].name
            picture = leaders[position].picture
            consultationsDay = leaders[position].consultationsDay
            consultationsTime = leaders[position].consultationsTime
            email = leaders[position].email
            phone = leaders[position].phoneNumber
            degree = leaders[position].degree
            room = leaders[position].room

            zoomActivity()
        }
    }

    private fun zoomActivity() {
        val intent = Intent(this, ZoomLeaderActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("picture", picture)
        intent.putExtra("day", consultationsDay)
        intent.putExtra("time", consultationsTime)
        intent.putExtra("degree", degree)
        intent.putExtra("phone", phone)
        intent.putExtra("email", email)
        intent.putExtra("room", room)

        setResult(Activity.RESULT_OK, intent)
        startActivityForResult(intent, 0)
    }



}
