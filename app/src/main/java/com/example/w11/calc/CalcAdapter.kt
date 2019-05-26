package com.example.w11.calc

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.w11.R
import kotlinx.android.synthetic.main.calc_main.*
import kotlinx.android.synthetic.main.list_course.view.*


class CalcAdapter(var courses: ArrayList<Course>, private var context: Context) :
    RecyclerView.Adapter<CalcAdapter.ViewHolder>() {

    var isSelected = false

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_course, parent, false))
    }


    fun calcavg(): String {
        var sumECTS = courses.sumByDouble { it.ECTS.toDouble() }
        var sumGr = courses.sumByDouble { it.grade.toDouble() * it.ECTS.toDouble() }
        var res = (sumGr / sumECTS)
        return "%.2f".format(res)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, courses, isSelected)
        holder.deleteButton.setOnClickListener {
            courses.removeAt(position)
            if (courses.isEmpty()) {
                isSelected = false
                (context as CalcMain).selectAll.visibility = View.GONE
                (context as CalcMain).deleteAll.visibility = View.GONE
            }
            notifyDataSetChanged()
            (context as CalcMain).avg.text = "Srednia: ${calcavg()}"
        }

        holder.view.setOnLongClickListener {
            isSelected = !isSelected
            (context as CalcMain).selectAll.visibility = View.VISIBLE
            (context as CalcMain).deleteAll.visibility = View.VISIBLE
            if (!isSelected) {
                courses.stream().forEach { it.isChecked = false }
                (context as CalcMain).selectAll.visibility = View.GONE
                (context as CalcMain).deleteAll.visibility = View.GONE
            }
            notifyDataSetChanged()
            true
        }

        holder.box.setOnClickListener {
            courses[position].isChecked = holder.box.isChecked
        }

        if (isSelected) {
            holder.view.setOnClickListener {
                courses[position].isChecked = !courses[position].isChecked
                holder.box.isChecked = courses[position].isChecked
            }
        } else holder.view.setOnClickListener {

        }

    }


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var course = view.course
        var ects = view.ects
        var grade = view.grade

        var deleteButton = view.deleteItem
        //var editButton = view.editItem
        var box = view.checkBox

        fun bind(
            position: Int,
            courses: ArrayList<Course>,
            selected: Boolean
        ) {

            course.text = courses[position].course
            ects.text = "ECTS: ${courses[position].ECTS}"
            grade.text = "Ocena: ${courses[position].grade}"
            box.isChecked = courses[position].isChecked
            if (selected) {
                deleteButton.visibility = View.GONE
                //editButton.visibility = View.GONE
                box.visibility = View.VISIBLE
            } else {
                deleteButton.visibility = View.VISIBLE
                //editButton.visibility = View.VISIBLE
                box.visibility = View.GONE
            }


        }


    }


}










