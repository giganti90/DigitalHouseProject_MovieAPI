package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.ui.activity.movies.CategoryDetailActivity
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieDetailsActivity
import com.squareup.picasso.Picasso

class CategoryDetailAdapter(var list:MutableList<MovieResponse>): RecyclerView.Adapter<CategoryDetailViewHolder>() {

    init {
        Log.d("category detail adapter", "init do category detail adapter ${list.size}")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        Log.d("category detail adapter", "chamando oncreateViewHolder")

        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_category_detail, parent, false)
        return CategoryDetailViewHolder(view);
    }


    override fun getItemCount(): Int {
        val size = this.list.size
        Log.d("category detail adapter", "item count ${size}")
        return size
        }

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        holder.title.text = list[position].title

        Log.d("category detail adapter", "carregando item: ${list[position].title}")


//        Toast.makeText(
//                holder.itemView.context,
//                "CARREGANDO O ITEM:\n${list[position].title}",
//                Toast.LENGTH_SHORT
//        ).show()


//        Picasso
//            .get()
//            .load(list[position].posterPath)
//            //.placeholder(ContextCompat.getDrawable(, R.drawable.ic_loader))
//            .into(holder.image);

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, MovieDetailsActivity::class.java)
                        .putExtra("id", list[position].id)
            )
        }

    }
}

class CategoryDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_detcat_tvTitle) }
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_cat_det_ivImage) }
}
