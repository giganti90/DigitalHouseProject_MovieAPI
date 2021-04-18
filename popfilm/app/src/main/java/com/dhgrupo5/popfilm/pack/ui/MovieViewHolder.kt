package com.dhgrupo5.popfilm.pack.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.layout_list_cat_det_ivImage)
    val title: TextView = itemView.findViewById(R.id.layout_lista_detcat_tvTitle)
}
