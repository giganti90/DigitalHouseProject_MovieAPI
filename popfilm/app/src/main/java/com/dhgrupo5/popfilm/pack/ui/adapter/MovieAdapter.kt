package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Result
import com.dhgrupo5.popfilm.pack.ui.MovieViewHolder
import com.squareup.picasso.Picasso

class MovieAdapter(
        private val list: List<Result>,
        private val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_category_detail, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list.elementAt(position)
        Picasso.get().load(movie.posterPath.size).into(holder.image)
        holder.title.text = movie.title
    }

}
