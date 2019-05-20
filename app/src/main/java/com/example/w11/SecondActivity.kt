package com.example.w11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.drawable.GradientDrawable
import android.os.AsyncTask
import android.util.Log
import android.widget.ListView

class SecondActivity : AppCompatActivity() {

    var consults = ArrayList<Consultation>()
    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_dates)

        val listView = findViewById<ListView>(R.id.list_of_dates)

        val colors = intArrayOf(0, -0x006400, 0)
        listView.divider = GradientDrawable(Orientation.RIGHT_LEFT, colors)
        listView.dividerHeight = 1

        AsyncTask.execute {
            database = AppDatabase.getInstance(this)
            consults = database!!.consultationDao().getAll() as ArrayList<Consultation>
            this@SecondActivity.runOnUiThread({
                listView.adapter = MyDateAdapter(this, R.layout.one_date, consults)
            })
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            AsyncTask.execute {
                database!!.consultationDao().delete(consults.get(position).name)
            }
            Thread(Runnable {
                this@SecondActivity.runOnUiThread({
                    consults.removeAt(position)
                    listView.adapter = MyDateAdapter(this, R.layout.one_date, consults)
                })
            }).start()
            true
        }

    }
}
