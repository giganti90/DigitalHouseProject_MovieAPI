package com.dhgrupo5.popfilm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.MenuItem


class MenuAdapter(
        val context: Context,
        val items: List<MenuItem>,
        private val changeFragmentCallback: (MenuItem)->Unit
    ) : RecyclerView.Adapter<MenuViewHolder>() {

    companion object{

        const val NUMBER_COLUMNS = 1
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int ) : MenuViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.menu_item,
                parent,
                false
            )

        return MenuViewHolder(
            adapter = this,
            changeFragmentCallback = changeFragmentCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int ){

        holder.setModel(
            item = items[ position ]
        )
    }

    override fun getItemCount()
        = items.size
}