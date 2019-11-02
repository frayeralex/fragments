package com.github.frayeralex.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val dataSet: ArrayList<CarModel>,
    private val handlerInterface: SelectCarHandlerInterface
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    class ViewHolder(
        v: View,
        handlerInterface: SelectCarHandlerInterface,
        dataSet: ArrayList<CarModel>
    ) : RecyclerView.ViewHolder(v) {
        val idView: TextView

        init {
            v.setOnClickListener {
                handlerInterface.onCarSelect(dataSet[adapterPosition])
            }
            idView = v.findViewById(R.id.idLabel)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_item, viewGroup, false)

        return ViewHolder(v, handlerInterface, dataSet)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idView.text = dataSet[position].model
    }

    override fun getItemCount() = dataSet.size

}