package com.example.w11.calc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.w11.R
import kotlinx.android.synthetic.main.calc_main.*
import kotlinx.android.synthetic.main.subject_details.*

class CalcMain : AppCompatActivity() {

    var courses = ArrayList<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calc_main)


        ArrayAdapter.createFromResource(
            this,
            R.array.grade_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            gradeSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.ects_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            ectsSpinner.adapter = adapter
        }


        ectsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

        }

        gradeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //wGrade.text =
            }

        }


        coursesList.layoutManager = LinearLayoutManager(this)
        coursesList.adapter = CalcAdapter(courses, this)


        addButton.setOnClickListener {
            (coursesList.adapter as CalcAdapter).courses.add(
                Course(
                    newCourse.text.toString(),
                    ectsSpinner.selectedItem.toString(),
                    gradeSpinner.selectedItem.toString()
                )
            )
            // (coursesList.adapter as CalcAdapter).notifyDataSetChanged()
            coursesList.scrollToPosition((coursesList.adapter as CalcAdapter).courses.size - 1)
            (coursesList.adapter as CalcAdapter).notifyItemInserted(courses.size)

            var sumECTS = courses.sumByDouble { it.ECTS.toDouble() }
            var sumGr = courses.sumByDouble { it.grade.toDouble() * it.ECTS.toDouble() }
            var res = (sumGr / sumECTS)
            val txt = "%.2f".format(res)
            avg.text = "Srednia: $txt"
        }

        selectAll.setOnClickListener {
            courses.stream().forEach {
                it.isChecked = selectAll.isChecked
            }
            (coursesList.adapter as CalcAdapter).notifyDataSetChanged()
        }

        deleteAll.setOnClickListener {
            var iter = courses.iterator()
            while (iter.hasNext()) {
                if (iter.next().isChecked) iter.remove()
            }
            //if(courses.isEmpty()){
            (coursesList.adapter as CalcAdapter).isSelected = false
            selectAll.visibility = View.GONE
            deleteAll.visibility = View.GONE
            //  }
            (coursesList.adapter as CalcAdapter).notifyDataSetChanged()
            avg.text = "Srednia: ${calcavg()}"

        }
    }


    fun calcavg(): String {
        var sumECTS = courses.sumByDouble { it.ECTS.toDouble() }
        var sumGr = courses.sumByDouble { it.grade.toDouble() * it.ECTS.toDouble() }
        var res = (sumGr / sumECTS)
        return "%.2f".format(res)
    }
}
