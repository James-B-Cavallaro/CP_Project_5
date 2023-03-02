package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class EntryAdapter(private val context: Context, private val entries: MutableList<InputData>) : RecyclerView.Adapter<EntryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_entry, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EntryAdapter.ViewHolder, position: Int) {
        val entry = entries[position]
        holder.nameTextView.text = "Mood: ${entry.mood}"
        holder.dateTextView.text = "Date: ${entry.date}"
        holder.ratingTextView.text = "Rating: ${entry.rating}"
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.moodName)
        val dateTextView: TextView = itemView.findViewById(R.id.moodDate)
        val ratingTextView: TextView = itemView.findViewById(R.id.moodRating)
    }
}