package com.dhgrupo5.popfilm.pack.ui.adapter

class FilmDetailsAdapter(var film:MutableList<MovieResponse>): RecyclerView.Adapter<FilmDetailsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmDetailsVH {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_category_detail, parent, false)
        return FilmDetailsVH(view);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holderForFilmDetails: CategoryInfoViewHolderForCategories, position: Int) {
        holderForFilmDetails.title.text = film[position].title
        val movie = list[position]

        // Picasso
        val imageSize = "w500"
        val posterUrl = "${NetworkUtils.IMG_BASE_URL}$imageSize${movie.posterPath}"
        Picasso.get().load(posterUrl).into(holderForFilmDetails.image)

        holderForFilmDetails.itemView.setOnClickListener {
            holderForFilmDetails.itemView.context.startActivity(
                Intent(holderForFilmDetails.itemView.context, MovieDetailsActivity::class.java)
                    .putExtra("id", list[position].id)
                    .putExtra("title", list[position].name)

            )
        }
    }
}

class FilmDetailsVH(view : View) : RecyclerView.ViewHolder(view){
    val title by lazy { view.findViewById<TextView>(R.id.layout_lista_detcat_tvTitle) }
    val image by lazy { view.findViewById<ImageView>(R.id.layout_list_cat_det_ivImage) }

}
