package com.dhgrupo5.popfilm.pack.ui.recycleradapter

import java.util.*

object Parent {
        private val random = Random()

        private val titles =  arrayListOf( "Destaques              >", "Ação              >", "Comédia              >",
            "Suspense              >", "Terror              >", "Documentários              >", "Aventura              >")

        private fun randomTitle() : String{
            val index = random.nextInt(titles.size)
            return titles[index]
        }

        private fun randomChildren() : List<ChildModel>{
            return Child.getChildren(20)
        }

        fun getParents(count : Int) : List<ParentModel>{
            val parents = mutableListOf<ParentModel>()
            repeat(count){
                val parent = ParentModel(randomTitle(), randomChildren())
                parents.add(parent)
            }
            return parents
        }
    }
