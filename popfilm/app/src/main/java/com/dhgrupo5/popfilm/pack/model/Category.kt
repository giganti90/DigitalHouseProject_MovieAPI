package com.dhgrupo5.popfilm.pack.model

data class Category(val code:Int, val title:String, val url:String, var movies:MutableList<Movie>)