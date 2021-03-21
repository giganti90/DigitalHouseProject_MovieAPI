package com.example.dgpopfilms.home

import com.example.dgpopfilms.R
import java.util.*

object Child {

    private val random = Random()

    private val titles =  arrayListOf( "Titanic", "GoldenEye", "Macbeth", "Skyfall")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomImage() : Int{
        return R.drawable.bpposter
    }

    fun getChildren(count : Int) : List<ChildModel>{
        val children = mutableListOf<ChildModel>()
        repeat(count){
            val child = ChildModel(randomImage(), randomTitle())
            children.add(child)
        }
        return children
    }


}
