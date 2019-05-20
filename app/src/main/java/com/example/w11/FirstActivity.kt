package com.example.w11

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

class FirstActivity : AppCompatActivity() {

    private var leaders = arrayOf(
        Leader(R.drawable.macyna, "Wojciech Macyna", "starszy wykładowca", "wojciech.macyna@pwr.edu.pl", "71 320 3048", "210/D-1", "13:15", "Sroda"),
        Leader(R.drawable.blaskiewicz, "Przemek Błaśkiewicz", "adiunkt", "przemyslaw.blaskiewicz@pwr.edu.pl", "71 320 2326", "216/D-1", "10:00", "Sroda"),
        Leader(R.drawable.krupski, "Paweł Krupski", "profesor", "pawel.krupski@pwr.edu.pl", "71 320 3362", "217/D-1", "09:00", "Czwartek")
    )

    var dayOfWeek : String =""
    var chosenName : String =""
    var chosenRoom : String=""
    var chosenDate : String=""
    var chosenTime : String=""
    var names = ArrayList<String>()

    var database : AppDatabase? = null
    lateinit var spinner : Spinner 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)

        spinner = this.findViewById(R.id.spinner)

        val checkBox: CheckBox = this.findViewById(R.id.checkBox)
        val button: Button = findViewById(R.id.button)

        for (i in 0 until leaders.size){
            names.add(leaders[i].name)
        }

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                checkBox.text = " "
            }

            @SuppressLint("SetTextI18n")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if(leaders.get(position).consultationsDay.equals("Poniedzialek")){
                   dayOfWeek = "poniedziałek"
               }
                else if(leaders.get(position).consultationsDay.equals("Wtorek")){
                   dayOfWeek = "wtorek"
               }
                else if(leaders.get(position).consultationsDay.equals("Sroda")){
                   dayOfWeek = "środa"
               }
                else if(leaders.get(position).consultationsDay.equals("Czwartek")){
                   dayOfWeek = "czwartek"
               }
                else if(leaders.get(position).consultationsDay.equals("Piatek")){
                   dayOfWeek = "piątek"
               }

                checkBox.text =
                    """${leaders.get(position).consultationsDay} ${leaders.get(position).consultationsTime}"""

                chosenName = leaders.get(position).name
                chosenRoom = leaders.get(position).room
                chosenTime = leaders.get(position).consultationsTime
            }
        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                button.isClickable=true
                button.setOnClickListener {
                    dateDialog()
                }
            }
            else{
                button.isClickable=false
            }
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
            val dayOfWeek1 = simpledateformat.format(date1)


            if(dayOfWeek != dayOfWeek1){
               Toast.makeText(this, "Błąd! Wybierz poprawny dzień ("+dayOfWeek+")",Toast.LENGTH_SHORT).show()

            }else{
                chosenDate = "0$dayOfMonth".takeLast(2) + "-" + "0${monthOfYear+1}".takeLast(2) + "-" + year
                var tester = Consultation(chosenName, chosenRoom, chosenDate, chosenTime)
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
