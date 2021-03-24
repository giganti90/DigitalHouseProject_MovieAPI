package com.example.dgpopfilms.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R


class ChildAdapter (private val children : List<ChildModel>)
        : RecyclerView.Adapter<ChildAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v =  LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_child,parent,false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return children.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val child = children[position]
            holder.imageView.setImageResource(child.image)
            holder.textView.text = child.title
        }


        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

            val textView : TextView = itemView.findViewById<TextView>(R.id.child_textView)
            val imageView: ImageView = itemView.findViewById<ImageView>(R.id.child_imageView)

        }
    }
