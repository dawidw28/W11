package com.example.w11

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteConstraintException
import android.os.AsyncTask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.first_activity.*
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

class FirstActivity : AppCompatActivity() {

    private var leaders = arrayOf(
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

    var dayOfWeek : String =""
    var dayOfWeek1 : String =""
    var dayOfWeek2 : String =""
    var chosenName : String =""
    var chosenRoom : String=""
    var chosenDate : String=""
    var chosenTime : String=""
    var names = ArrayList<String>()

    var pos : Int = 0

    private var database : AppDatabase? = null
    lateinit var spinner : Spinner 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)

        spinner = this.findViewById(R.id.spinner)


        val button: Button = findViewById(R.id.button)

        leaders.sortBy { it.name}

        for (i in 0 until leaders.size){
            names.add(leaders[i].name)
        }

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                radioButton.text = " "
                radioButton2.text = " "
            }

            @SuppressLint("SetTextI18n")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                radio_group.clearCheck()
                button.isClickable=false
                when {
                    leaders.get(position).consultationsDay1.equals("Poniedziałek") -> dayOfWeek1 = "poniedziałek"
                    leaders.get(position).consultationsDay1.equals("Wtorek") -> dayOfWeek1 = "wtorek"
                    leaders.get(position).consultationsDay1.equals("Środa") -> dayOfWeek1 = "środa"
                    leaders.get(position).consultationsDay1.equals("Czwartek") -> dayOfWeek1 = "czwartek"
                    leaders.get(position).consultationsDay1.equals("Piątek") -> dayOfWeek1 = "piątek"
                }

                when {
                    leaders.get(position).consultationsDay2.equals("Poniedziałek") -> dayOfWeek2 = "poniedziałek"
                    leaders.get(position).consultationsDay2.equals("Wtorek") -> dayOfWeek2 = "wtorek"
                    leaders.get(position).consultationsDay2.equals("Środa") -> dayOfWeek2 = "środa"
                    leaders.get(position).consultationsDay2.equals("Czwartek") -> dayOfWeek2 = "czwartek"
                    leaders.get(position).consultationsDay2.equals("Piątek") -> dayOfWeek2 = "piątek"
                }

                radioButton.text =
                    """${leaders.get(position).consultationsDay1} ${leaders.get(position).consultationsTime1}"""

                radioButton2.text =
                    """${leaders.get(position).consultationsDay2} ${leaders.get(position).consultationsTime2}"""

                chosenName = leaders.get(position).name
                chosenRoom = leaders.get(position).room

                pos = position
            }
        }


        radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button.isClickable=true
                chosenTime = leaders.get(pos).consultationsTime1
                dayOfWeek=dayOfWeek1
            }

        }
        radioButton2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button.isClickable=true
                chosenTime = leaders.get(pos).consultationsTime2
                dayOfWeek=dayOfWeek2
            }
        }

        button.setOnClickListener{
            if(button.isClickable) dateDialog()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun dateDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, _, monthOfYear, dayOfMonth ->


            val simpledateformat = SimpleDateFormat("EEEE")
            val date1 = Date(year, monthOfYear, dayOfMonth- 1)
            val dayOfW = simpledateformat.format(date1)


            if(dayOfWeek != dayOfW){
               Toast.makeText(this, "Błąd! Wybierz poprawny dzień! ($dayOfWeek)",Toast.LENGTH_SHORT).show()

            }else{
                chosenDate = "0$dayOfMonth".takeLast(2) + "-" + "0${monthOfYear+1}".takeLast(2) + "-" + year
                val tester = Consultation(chosenName, chosenRoom, chosenDate, chosenTime)
                AsyncTask.execute {
                    database = AppDatabase.getInstance(this)
                    try {
                        database!!.consultationDao().insert(tester)
                        this@FirstActivity.runOnUiThread {
                            Toast.makeText(this, "Gratulacje! Zapisałeś się na konsultacje! ", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    catch (e : SQLiteConstraintException) {
                        this@FirstActivity.runOnUiThread {
                            Toast.makeText(this, "Błąd! Już jesteś zapisany na konsultacje do tego prowadzącego! ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


        }, year, month, day)

        dialog.show()

    }

}
