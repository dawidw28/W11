package com.example.w11.courses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.w11.R

class MyCourseAdapter(context: Context, var data: ArrayList<Course>): ArrayAdapter<Course>(context, R.layout.course_list_item, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.course_list_item, parent, false)
        }
        val id = data[position].picture

        view!!.findViewById<ImageView>(R.id.typeImage).setImageResource(id)
        view!!.findViewById<TextView>(R.id.courseName).text = data[position].name
        view!!.findViewById<TextView>(R.id.lecturer).text = data[position].teacher

        return view
    }
}