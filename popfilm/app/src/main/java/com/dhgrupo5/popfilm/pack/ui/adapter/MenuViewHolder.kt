package com.dhgrupo5.popfilm.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.MenuItem
import com.dhgrupo5.popfilm.model.MenuItemStatus


class MenuViewHolder(
        private val adapter: MenuAdapter,
        private val changeFragmentCallback: (MenuItem)->Unit,
        itemView: View
) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    private var ivIcon: ImageView
    private var tvLabel: TextView

    init {
        itemView.setOnClickListener( this )

        ivIcon = itemView.findViewById( R.id.iv_icon )
        tvLabel = itemView.findViewById( R.id.tv_label )
    }


    fun setModel( item: MenuItem ) {
        tvLabel.text = item.label
        ivIcon.setImageResource( item.icon )
        ivIcon.contentDescription = item.label

        setStyle( item = item )
    }


    private fun setStyle( item: MenuItem ){

        var itemColor = R.color.colorMenuItemNotSelected

        if( item.isSelected == MenuItemStatus.SELECTED ){
            itemColor = R.color.colorMenuItemSelected
        }

        val color = ContextCompat.getColor(
                adapter.context,
                itemColor
        )

        ivIcon.setColorFilter( color )
        tvLabel.setTextColor( color )
    }

    override fun onClick( view: View ){

        val oldSelectedItem = adapter.items.first {
            it.isSelected == MenuItemStatus.SELECTED
        }
        val indexOldSelectedItem = adapter.items.indexOf( oldSelectedItem )

        if( indexOldSelectedItem == adapterPosition ){
            return
        }


        oldSelectedItem.apply {
            isSelected = MenuItemStatus.NOT_SELECTED
        }
        adapter.notifyItemChanged( indexOldSelectedItem )

        adapter.items[ adapterPosition ].apply {
            isSelected = MenuItemStatus.SELECTED
        }
        adapter.notifyItemChanged(
                adapterPosition
        )

        changeFragmentCallback(
                adapter.items[ adapterPosition ]
        )
    }
}