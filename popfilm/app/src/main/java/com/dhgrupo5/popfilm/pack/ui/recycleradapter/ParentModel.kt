package com.dhgrupo5.popfilm.pack.ui.recycleradapter

import com.dhgrupo5.popfilm.pack.ui.recycleradapter.ChildModel

data class ParentModel (
    val title : String = "",
    val children : List<ChildModel> = listOf()
    )
