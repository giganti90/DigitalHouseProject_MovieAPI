package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.ui.MovieViewHolder
import com.dhgrupo5.popfilm.pack.ui.activity.movies.CategoryDetailActivity
import com.squareup.picasso.Picasso

class MovieAdapter(private val list :MutableList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_categories, parent, false);
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list.elementAt(position)
        Picasso.get().load(movie.poster_path).into(holder.image)
        holder.title.text = movie.title


//        Picasso
//            .get()
//            .load(list[position].url)
//            //.placeholder(ContextCompat.getDrawable(, R.drawable.ic_loader))
//            .transform(CropCircleTransformation())
//            .resize(200, 200)
//            .into(holder.image);

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Você clicou na categoria:\n${list[position].title}",
                Toast.LENGTH_SHORT
            ).show()

            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, CategoryDetailActivity::class.java)
                    .putExtra("id", list[position].id)
                    .putExtra("title", list[position].title)
                    .putExtra("overview", list[position].overview)
                    .putExtra("release date", list[position].release_date)
//                        .putExtra("listMovie", Gson().toJson(list[position].movies))
            )
        }

    }

}
