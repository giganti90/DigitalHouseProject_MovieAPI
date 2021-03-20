package com.dhgrupo5.popfilm.model

class MenuItem(
        val id: Int,
        val label: String,
        val icon: Int,
        var isSelected: MenuItemStatus = MenuItemStatus.NOT_SELECTED )