package com.dhgrupo5.popfilm.pack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.MovieResponse
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieActivity
import com.dhgrupo5.popfilm.pack.ui.activity.movies.MovieDetailsActivity

class CategoryInfoAdapterForCategories(var list:MutableList<MovieResponse>): RecyclerView.Adapter<CategoryInfoViewHolderForCategories>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryInfoViewHolderForCategories {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_category_detail, parent, false)
        return CategoryInfoViewHolderForCategories(view);
    }


    override fun getItemCount(): Int {
        return list.size
        }


    override fun onBindViewHolder(holderForCategories: CategoryInfoViewHolderForCategories, position: Int) {
        holderForCategories.title.text = list[position].title

//        Toast.makeText(
//                holder.itemView.context,
//                "CARREGANDO O ITEM:\n${list[position].title}",
//                Toast.LENGTH_SHORT
//        ).show()

        holderForCategories.itemView.setOnClickListener {
            holderForCategories.itemView.context.startActivity(
                    Intent(holderForCategories.itemView.context, MovieDetailsActivity::class.java)
                            .putExtra("id", list[position].id)
            )
        }

//        val position = list[position]
//        val imageUrl = "${Image()?.secure_base_url}${Image()?.poster_sizes?.get(0)}${position.posterPath}"
//        val image = holder.image
//        Picasso.get().load(imageUrl).into(image)
//        holder.image.setOnClickListener {
//            val intent = Intent(it.context, MovieDetailsActivity::class.java)
//            intent.putExtra("id", position.id)
//            it.context.startActivity(intent)
//        }

    }
}

class CategoryInfoViewHolderForCategories(view : View) : RecyclerView.ViewHolder(view){
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_detcat_tvTitle) }
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_cat_det_ivImage) }
}
