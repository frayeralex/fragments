package com.github.frayeralex.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: ArrayList<CarModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val idView: TextView

        init {
            v.setOnClickListener { println("Element $adapterPosition clicked.") }
            idView = v.findViewById(R.id.idLabel)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_item, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idView.text = dataSet[position].id
    }

    override fun getItemCount() = dataSet.size

}