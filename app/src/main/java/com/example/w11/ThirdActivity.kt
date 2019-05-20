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
    var consultationsTime1 =""
    var consultationsTime2 =""
    private var consultationsDay1 =""
    private var consultationsDay2 =""
    var degree = ""
    var room = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_leaders)

        val listView = findViewById<ListView>(R.id.list_of_leaders)

       leaders = arrayListOf(
            Leader(R.drawable.macyna, "Macyna Wojciech ", "starszy wykładowca", "wojciech.macyna@pwr.edu.pl", "71 320 3048", "210/D-1", "13:15", "9:00", "Środa", "Piątek"),
            Leader(R.drawable.blaskiewicz, "Błaśkiewicz Przemek ", "adiunkt", "przemyslaw.blaskiewicz@pwr.edu.pl", "71 320 2326", "216/D-1", "11:00", "10:00" ,"Poniedziałek", "Środa"),
            Leader(R.drawable.krupski, "Krupski Paweł ", "profesor", "pawel.krupski@pwr.edu.pl", "71 320 3362", "217/D-1", "13:00", "09:00","Poniedziałek", "Czwartek") ,
            Leader(R.drawable.morayne, "Morayne Michał", "profesor", "michal.morayne@pwr.edu.pl", "71 320 3362", "217/D-1", "13:00", "19:00","Wtorek", "Piątek"),
            Leader(R.drawable.zeberski, "Żeberski Szymon", "adiunkt", "szymon.zeberski@pwr.edu.pl", "71 320 3362", "217/D-1", "11:00", "17:00","Środa", "Piątek"),
            Leader(R.drawable.gebala, "Gębala Maciej", "starszy wykładowca", "maciej.gebala@pwr.edu.pl", "71 320 3303", "214/D-1", "09:00", "11:00","Czwartek", "Piątek"),
            Leader(R.drawable.kapelko, "Kapelko Rafał", "adiunkt", " rafal.kapelko@pwr.edu.pl", "71 320 3048", "210/D-1", "11:00", "09:00","Poniedziałek", "Czwartek"),
            Leader(R.drawable.kobylanski, "Kobylański Przemysław", "starszy wykładowca", "przemyslaw.kobylanski@pwr.edu.pl", "71 320 3048", "210/D-1", "09:00", "09:00","Poniedziałek", "Czwartek"),
            Leader(R.drawable.michalski, "Michalski Marcin", "asystent", "Marcin.k.Michalski@pwr.edu.pl", "71 320 3498", "218/D-1", "13:00", "11:00","Poniedziałek", "Wtorek"),
            Leader(R.drawable.krzywiecki, "Krzywiecki Łukasz", "adiunkt", "lukasz.krzywiecki@pwr.edu.pl", "71 320 3048", "210/D-1", "17:00", "15:00","Czwartek", "Piątek")
        )

        leaders.sortBy { it.name}

        listView.adapter = MyLeaderAdapter(this, R.layout.one_leader, leaders)

        listView.setOnItemClickListener { _, _, position, _ ->
            choice = position
            name = leaders[position].name
            picture = leaders[position].picture
            consultationsDay1 = leaders[position].consultationsDay1
            consultationsDay2 = leaders[position].consultationsDay2
            consultationsTime1 = leaders[position].consultationsTime1
            consultationsTime2 = leaders[position].consultationsTime2
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
        intent.putExtra("day1", consultationsDay1)
        intent.putExtra("time1", consultationsTime1)
        intent.putExtra("day2", consultationsDay2)
        intent.putExtra("time2", consultationsTime2)
        intent.putExtra("degree", degree)
        intent.putExtra("phone", phone)
        intent.putExtra("email", email)
        intent.putExtra("room", room)

        setResult(Activity.RESULT_OK, intent)
        startActivityForResult(intent, 0)
    }



}
