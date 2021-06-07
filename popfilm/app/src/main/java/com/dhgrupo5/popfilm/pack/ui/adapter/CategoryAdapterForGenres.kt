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
import com.dhgrupo5.popfilm.pack.ui.activity.movies.CategoryDetailActivity
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories
import com.dhgrupo5.popfilm.pack.utils.moviesdb.NetworkUtils
import com.squareup.picasso.Picasso

class CategoryAdapterForGenres(private val list :MutableList<GenreDCModelForCategories>) : RecyclerView.Adapter<CategoryViewHolderForGenres>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolderForGenres {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_categories, parent, false);
        return CategoryViewHolderForGenres(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holderForGenres: CategoryViewHolderForGenres, position: Int) {
        holderForGenres.title.text = list[position].name

        list[position].movies.let { movies ->
            val randomMovieNumber = (Math.random() * 10).toInt()
            val posterPath = movies[randomMovieNumber].posterPath
            val posterUrl = "${NetworkUtils.IMG_BASE_URL}w500$posterPath"
            Picasso.get().load(posterUrl).into(holderForGenres.image)
        }

        holderForGenres.itemView.setOnClickListener {
            Toast.makeText(
                holderForGenres.itemView.context,
                "Você clicou na categoria:\n${list[position].name}",
                Toast.LENGTH_SHORT
            ).show()

            holderForGenres.itemView.context.startActivity(
                Intent(holderForGenres.itemView.context, CategoryDetailActivity::class.java)
                    .putExtra("id", list[position].id)
                    .putExtra("title", list[position].name)
//                        .putExtra("listMovie", Gson().toJson(list[position].movies))
            )
        }

    }

}

class CategoryViewHolderForGenres(view : View) : RecyclerView.ViewHolder(view){
    val image by lazy { view.findViewById<ImageView>(R.id.layout_lista_cat_ivImage) }
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_cat_tvTitle) }
}
