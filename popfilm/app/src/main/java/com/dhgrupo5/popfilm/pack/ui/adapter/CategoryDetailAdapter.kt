package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.activity.CategoryDetailActivity
import com.dhgrupo5.popfilm.pack.model.Movie
import com.squareup.picasso.Picasso

class CategoryDetailAdapter(var list:MutableList<Movie>): RecyclerView.Adapter<CategoryDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_category_detail, parent, false)
        return CategoryDetailViewHolder(view);
    }

    override fun getItemCount() = this.list.size

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {

        Picasso
            .get()
            .load(list[position].url)
            //.placeholder(ContextCompat.getDrawable(, R.drawable.ic_loader))
            .into(holder.image);

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, CategoryDetailActivity::class.java)
            )
        }

    }
}

class CategoryDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_cat_det_ivImage) }
}