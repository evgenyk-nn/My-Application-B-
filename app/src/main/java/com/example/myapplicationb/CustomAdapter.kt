package com.example.myapplicationb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Result>?) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList?.get(position)

        // Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.imageView)
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList?.get(position)?.poster_path).into(holder.imageView)
        // sets the image to the imageview from our itemHolder class
        // holder.imageView.setImageResource(ItemsViewModel.image)
        // holder.textView.text = ItemsViewModel?.title
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        // val textView: TextView = itemView.findViewById(R.id.textView)
    }
}