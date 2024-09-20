package com.faviansa.submissionandroidpemula

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMusicianAdapter(private val listMusician: ArrayList<Musician>) :
    RecyclerView.Adapter<ListMusicianAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun getItemCount(): Int = listMusician.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_musician, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, favSong) = listMusician[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val musicianData = Musician(
                name, description, photo, favSong
            )
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("EXTRA_MUSICIAN", musicianData)
            }
            holder.itemView.context.startActivity(intentDetail)
        }
    }


}