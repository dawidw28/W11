package com.example.w11.gallery


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.w11.R
import kotlinx.android.synthetic.main.recycler_item2.view.*

class MyAdapter2(
    private val images: ArrayList<Int>, private val context: Context
) : RecyclerView.Adapter<ViewHolder2>() {

    var set = false

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        var he = LayoutInflater.from(context).inflate(R.layout.recycler_item2, parent, false)
        return ViewHolder2(he)
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder.img.setOnClickListener {
            val intent = Intent(context, ImageFullScreen::class.java)
            intent.putIntegerArrayListExtra("Item", images)
            intent.putExtra("Pos", position)
            (context as Activity).startActivityForResult(intent, 666)
        }
        holder.img.setImageResource(images.get(position))
    }

}

class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    var img = view.singleImage
}



