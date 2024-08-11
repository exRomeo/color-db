package com.example.roomdbdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RCVAdapter(var colorsList: List<ColorModel>) :
    RecyclerView.Adapter<RCVAdapter.ColorViewHolder>() {
    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView = itemView.findViewById<LinearLayout>(R.id.color_view)
        val colorName = itemView.findViewById<TextView>(R.id.color_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return colorsList.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.colorView.setBackgroundColor(colorsList[position].getColor())
        holder.colorName.text = colorsList[position].name
    }

    fun updateData(colorsList: List<ColorModel>) {
        this.colorsList = colorsList
        notifyDataSetChanged()
    }

}