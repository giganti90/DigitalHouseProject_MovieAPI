package com.dhgrupo5.popfilm.pack.ui.activity.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.GenreDCModelForCategories

class HomeGenresAdapter(private val genres: MutableList<GenreDCModelForCategories>) :
    RecyclerView.Adapter<HomeGenresViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeGenresViewHolder(LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recyclerview_parent, parent, false
            )
        )

    override fun onBindViewHolder(holder: HomeGenresViewHolder, position: Int) {
        val genre = genres[position]
        holder.textView.text = genre.name
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeMoviesAdapter(genre.movies)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount() = genres.size
}

class HomeGenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val recyclerView : RecyclerView = itemView.findViewById(R.id.child_recycler)
    val textView: TextView = itemView.findViewById(R.id.textView)
}
