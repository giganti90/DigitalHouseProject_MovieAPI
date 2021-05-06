package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.DiscoverResponse
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.ui.activity.movies.DummyCategoryDetail
import com.squareup.picasso.Picasso

class MovieAdapter(private val discover: DiscoverResponse) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_categories, parent, false);
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return discover.movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = discover.movies[position].title


        holder.itemView.context.startActivity(
                Intent(holder.itemView.context, DummyCategoryDetail::class.java)
                       .putExtra("id", discover.movies[position].id)
                        .putExtra("title", discover.movies[position].title)
//                        .putExtra("listMovie", Gson().toJson(list[position].movies))
        )
    }

}


class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_cat_det_ivImage) }
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_detcat_tvTitle) }
}
