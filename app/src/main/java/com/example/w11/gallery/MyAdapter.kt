package com.example.w11.gallery


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.w11.R
import kotlinx.android.synthetic.main.recycler_item.view.*


class MyAdapter(private val events: Array<Event>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.star.setOnClickListener {
            val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            this.events[position].fav = !this.events[position].fav
            editor.putBoolean(this.events[position].title, this.events[position].fav)
            editor.apply()
            notifyItemChanged(position)
            println("$position")
            val temp = this.events[position]
            events.sortWith(compareByDescending<Event> { it.fav }.thenByDescending { it.date })
            val index = events.indexOf(temp)
            println("ee$index")
            if (index != position) notifyItemMoved(position, index)
            if (position - index < 0) notifyItemRangeChanged(
                position,
                Math.abs(position - index) + 1
            ) else notifyItemRangeChanged(index, Math.abs(position - index) + 1)


        }

        val count = events[position].imagesID.size
        val songsFound = context.resources.getQuantityString(R.plurals.numberOfPhotos, count, count)
        holder.bind(events[position], songsFound)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnTouchListener {

    lateinit var item: Event


    var title = view.desc
    var img = view.item_image
    var card = view.cardView
    var hiddenInfo = view.xdData
    var star = view.star
    var date = view.galleryDate
    var size = view.gallerySize

    init {
        view.setOnTouchListener(this)
    }

    fun bind(item: Event, quantity: String) {
        this.item = item
        title.text = item.title
        img.setImageResource(item.imagesID[0])
        card.setCardBackgroundColor(item.avgC)
        date.text = item.date.toString()
        size.text = quantity
        hiddenInfo.setCardBackgroundColor(item.avgC)
        if (item.fav) {
            star.setImageResource(R.drawable.star1)
        } else {
            star.setImageResource(R.drawable.star)
        }
    }

    override fun onTouch(v: View, e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                hiddenInfo.visibility = View.VISIBLE
                hiddenInfo.animate()
                    .alpha(0.8f)
                    .setDuration(1000)
                    .withEndAction {
                        hiddenInfo.animate()
                            .alpha(0.0f)
                            .setDuration(2000)
                            .withEndAction { hiddenInfo.visibility = View.GONE }
                    }

                return true

            }

            MotionEvent.ACTION_MOVE -> {
                return true
            }

            MotionEvent.ACTION_UP -> {
                val intent = Intent(v.context, SelectedEvent::class.java)
                intent.putExtra("Item", item)
                (v.context as Activity).startActivity(intent)
                hiddenInfo.visibility = View.GONE
                return true
            }

        }
        return false
    }

}








