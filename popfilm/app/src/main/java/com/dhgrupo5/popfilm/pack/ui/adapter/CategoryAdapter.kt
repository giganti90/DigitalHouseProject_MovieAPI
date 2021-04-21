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
import com.dhgrupo5.popfilm.pack.model.Category
import com.dhgrupo5.popfilm.pack.model.Genre
import com.dhgrupo5.popfilm.pack.ui.activity.movies.DummyCategoryDetail
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class CategoryAdapter(private val list :MutableList<Genre>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_categories, parent, false);
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = list[position].name

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
                "Você clicou na categoria:\n${list[position].name}",
                Toast.LENGTH_SHORT
            ).show()

            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, DummyCategoryDetail::class.java)
                    .putExtra("id", list[position].id)
                    .putExtra("title", list[position].name)
//                        .putExtra("listMovie", Gson().toJson(list[position].movies))
            )
        }

    }

}

class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val image by lazy { view.findViewById<ImageView>(R.id.layout_lista_cat_ivImage) }
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_cat_tvTitle) }
}
