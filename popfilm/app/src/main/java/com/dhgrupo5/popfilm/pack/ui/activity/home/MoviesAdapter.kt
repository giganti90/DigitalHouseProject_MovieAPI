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

class MoviesAdapter(val movies: MutableList<MovieResponse>) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_child, parent, false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        // Picasso
        holder.imageView.setImageResource(R.drawable.bpposter)
        holder.textView.text = movie.title

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            )
        }
    }

    override fun getItemCount() = movies.size

}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textView: TextView = itemView.findViewById(R.id.child_textView)
    val imageView: ImageView = itemView.findViewById(R.id.child_imageView)

}
