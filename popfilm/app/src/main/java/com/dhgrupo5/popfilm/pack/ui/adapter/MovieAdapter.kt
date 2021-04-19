package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.model.MovieConfig
import com.dhgrupo5.popfilm.pack.ui.MovieViewHolder
import com.dhgrupo5.popfilm.pack.ui.activity.movies.CategoryDetailActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.DummyCategoryDetail
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import com.squareup.picasso.Picasso
import java.util.*

class MovieAdapter(private val movielist :MutableList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_categories, parent, false);
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movielist.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movielist.elementAt(position)
        Picasso.get().load(movie.posterPath).into(holder.image)
        holder.title.text = movie.title
        val configuration = MovieConfig.config
        val position = movielist[position]
        val imageUrl = "${configuration?.images?.secure_base_url}${configuration?.images?.poster_sizes?.get(4)}${position.posterPath}"
        val image = holder.image
        Picasso.get().load(imageUrl).into(image)
        holder.image.setOnClickListener {
            val intent = Intent(it.context, DummyCategoryDetail::class.java)
            intent.putExtra("MOVIE_ID", position.id)
            it.context.startActivity(intent)
            }
    }
}

class MoviewViewHolder(view: View): RecyclerView.ViewHolder(view){
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_mov_ivImage) }
    val mRandom = Random()

    fun getRandomIntInRange(max: Int, min: Int): Int {
        return mRandom.nextInt(max - min + min) + min
    }
}
