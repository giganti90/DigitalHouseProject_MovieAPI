package com.dhgrupo5.popfilm.pack.ui.adapter

//class HomeCategoryAdapter(private val list :MutableList<Genre>) : RecyclerView.Adapter<HomeCategoryViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_home, parent, false);
//        return HomeCategoryViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
//        holder.title.text = list[position].name

//        Picasso
//            .get()
//            .load(list[position].url)
//            //.placeholder(ContextCompat.getDrawable(, R.drawable.ic_loader))
//            .transform(CropCircleTransformation())
//            .resize(200, 200)
//            .into(holder.image);

//        holder.itemView.setOnClickListener {
//            Toast.makeText(
//                holder.itemView.context,
//                "Você clicou na categoria:\n${list[position].name}",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            holder.itemView.context.startActivity(
//                Intent(holder.itemView.context, CategoryDetailActivity::class.java)
//                    .putExtra("id", list[position].id)
//                    .putExtra("title", list[position].name)
////                        .putExtra("listMovie", Gson().toJson(list[position].movies))
//            )
//        }
//
//    }
//
//}
//
//class HomeCategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
//    val title by lazy { view.findViewById<TextView>(R.id.title_categories) }
//}
