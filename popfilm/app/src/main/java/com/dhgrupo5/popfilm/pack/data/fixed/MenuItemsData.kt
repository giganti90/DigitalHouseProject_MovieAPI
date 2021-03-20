package com.dhgrupo5.popfilm.data.fixed

import android.content.res.Resources
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.model.MenuItem
import com.dhgrupo5.popfilm.model.MenuItemStatus


class MenuItemsData {

    companion object{

        fun getItems( res: Resources)
                = listOf(
            MenuItem(
                id = R.id.last_video,
                label = res.getString( R.string.item_menu_last_video ),
                icon = R.drawable.ic_circular_play,
                isSelected = MenuItemStatus.SELECTED
            ),
            MenuItem(
                id = R.id.social_networks,
                label = res.getString( R.string.item_menu_social_networks ),
                icon = R.drawable.ic_social_networks
            ),
            MenuItem(
                id = R.id.play_lists,
                label = res.getString( R.string.item_menu_play_lists ),
                icon = R.drawable.ic_play_lists
            ),


            MenuItem(
                id = R.id.about_channel,
                label = res.getString( R.string.item_menu_about_channel ),
                icon = R.drawable.ic_about
            ),


            MenuItem(
                id = R.id.business,
                label = res.getString( R.string.item_menu_business ),
                icon = R.drawable.ic_business
            )
        )
    }
}