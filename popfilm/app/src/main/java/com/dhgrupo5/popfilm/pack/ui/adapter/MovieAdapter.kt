package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieDetailsActivity
import com.dhgrupo5.popfilm.pack.model.Movie
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*

class MovieAdapter(private val list: MutableList<Movie>): RecyclerView.Adapter<MoviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_movie, parent, false)
        return MoviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {

        holder.image.layoutParams.height = holder.getRandomIntInRange(400, 250)

        Picasso
            .get()
            .load(list[position].url)
            //.placeholder(ContextCompat.getDrawable(, R.drawable.ic_loader))
            //.transform(CropCircleTransformation())
            .resize(200, 200)
            .into(holder.image);

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Você clicou no filme:\n${list[position].title}",
                Toast.LENGTH_SHORT
            ).show()

            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class MoviewViewHolder(view: View): RecyclerView.ViewHolder(view){
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_mov_ivImage) }
    val mRandom = Random()

    fun getRandomIntInRange(max: Int, min: Int): Int {
        return mRandom.nextInt(max - min + min) + min
    }
}
