package com.github.frayeralex.fragments.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.frayeralex.fragments.R
import com.github.frayeralex.fragments.models.CarModel
import kotlinx.android.synthetic.main.text_item.view.*

class CustomAdapter(
     private val dataSet: ArrayList<CarModel>,
     private val click : (CarModel)-> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.idLabel.text = dataSet[position].model
        viewHolder.itemView.setOnClickListener { click(dataSet[position]) }
    }

    override fun getItemCount() = dataSet.size
}