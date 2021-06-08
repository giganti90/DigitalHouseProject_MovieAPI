package com.dhgrupo5.popfilm.pack.ui.activity.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieDetailsActivity
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.squareup.picasso.Picasso

class HomeMoviesAdapter(val movies: MutableList<MovieResponse>) : RecyclerView.Adapter<HomeMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_child, parent, false
            )
        )

    override fun onBindViewHolder(holder: HomeMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.textView.text = movie.title
        // Picasso
        val imageSize = "w500"
        val posterUrl = "${NetworkUtils.IMG_BASE_URL}$imageSize${movie.posterPath}"
        Picasso.get().load(posterUrl).into(holder.imageView)

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, MovieDetailsActivity::class.java)
                    .putExtra("movie",movie)
            )
        }
    }

    override fun getItemCount() = movies.size

}

class HomeMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textView: TextView = itemView.findViewById(R.id.child_textView)
    val imageView: ImageView = itemView.findViewById(R.id.child_imageView)

}
