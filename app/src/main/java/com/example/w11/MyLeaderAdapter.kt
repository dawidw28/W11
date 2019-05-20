package com.example.w11

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyLeaderAdapter(var myContext: Context, var resource:Int, var leaders:List<Leader>)
    : ArrayAdapter<Leader>(myContext, resource, leaders){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)

        val view: View = layoutInflater.inflate(resource,null)
        val nameList: TextView = view.findViewById(R.id.list_name)

        val leaderArray: Leader = leaders[position]

        nameList.text = leaderArray.name

        return view
    }
}
