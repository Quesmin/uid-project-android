package com.example.uid_project.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uid_project.R
import com.example.uid_project.ui.main.adapters.model.ResidentListItemData

class ResidentsAdapter(private val dataList: ArrayList<ResidentListItemData>) : RecyclerView.Adapter<ResidentsAdapter.DataViewHolder>()  {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(resident: ResidentListItemData) {
            itemView.apply {
                itemView.findViewById<TextView>(R.id.residentNameTextValue).text = resident.name
                itemView.findViewById<TextView>(R.id.residentYobTextValue).text = resident.birthYear
                itemView.findViewById<TextView>(R.id.residentHeightTextValue).text = resident.height
                itemView.findViewById<TextView>(R.id.residentWeightTextValue).text = resident.mass
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.resident_list_item, parent, false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setResidents(residents: ArrayList<ResidentListItemData>) {
        this.dataList.apply {
            clear()
            addAll(residents)
        }

    }

}