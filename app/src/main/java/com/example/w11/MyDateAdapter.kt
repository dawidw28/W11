package com.example.w11

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyDateAdapter(var myContext: Context, var resource:Int, var dates:ArrayList<Consultation>)
    : ArrayAdapter<Consultation>(myContext, resource, dates){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)

        val view: View = layoutInflater.inflate(resource,null)
        val leaderText: TextView = view.findViewById(R.id.leader_text)
        val dateText: TextView = view.findViewById(R.id.date_text)
        val timeText: TextView = view.findViewById(R.id.time_text)
        val roomText: TextView = view.findViewById(R.id.room_text)

        leaderText.text = dates[position].name
        dateText.text = dates[position].date
        timeText.text = dates[position].time
        roomText.text = dates[position].room

        return view
    }
}