package com.dhgrupo5.popfilm.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.ListItem

class ListItemViewHolder(
    private val adapter: ListItemAdapter,
    private val callExternalAppCallback: (ListItem)->Unit,
    itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {


    private var ivIcon: ImageView
    private var tvMainText: TextView
    private var tvFirstAuxText: TextView
    private var tvSecondAuxText: TextView


    init {
        itemView.setOnClickListener( this )

        ivIcon = itemView.findViewById( R.id.iv_icon )
        tvMainText = itemView.findViewById( R.id.tv_main_text )
        tvFirstAuxText = itemView.findViewById( R.id.tv_first_aux_text )
        tvSecondAuxText = itemView.findViewById( R.id.tv_second_aux_text )
    }

    fun setModel( item: ListItem ) {
        ivIcon.setImageResource( item.getIcon() )
        ivIcon.contentDescription = item.getMainText()

        tvMainText.text = item.getMainText()
        setAuxTexts( item = item )
    }

    private fun setAuxTexts( item: ListItem ){

        if( item.getFirstAuxText().isNotEmpty() ){
            tvFirstAuxText.text = item.getFirstAuxText()
            tvFirstAuxText.visibility = View.VISIBLE

            val secondText = item.getSecondAuxText(
                resource = adapter.context.resources
            )

            if( secondText.isNotEmpty() ){
                tvSecondAuxText.text = secondText
                tvSecondAuxText.visibility = View.VISIBLE
            }
            else{
                tvSecondAuxText.visibility = View.GONE
            }
        }
        else{
            tvFirstAuxText.visibility = View.GONE
            tvSecondAuxText.visibility = View.GONE
        }
    }

    override fun onClick( view: View ) {
        callExternalAppCallback(
            adapter.items[ adapterPosition ]
        )
    }
}