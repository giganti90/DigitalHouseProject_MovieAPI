package com.dhgrupo5.popfilm.model.parse.playlist

class ItemParse(
    val id: String,
    private val snippet: SnippetParse? ){

    val title: String
        get() : String {
            return if( snippet != null ){
                snippet.title
            }
            else{
                ""
            }
        }
}