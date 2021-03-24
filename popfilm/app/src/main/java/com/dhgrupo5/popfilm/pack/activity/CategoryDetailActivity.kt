package com.dhgrupo5.popfilm.pack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.pack.model.Movie
import com.dhgrupo5.popfilm.pack.ui.adapter.CategoryDetailAdapter

class CategoryDetailActivity : AppCompatActivity() {

    val toolbar by lazy { findViewById<Toolbar>(R.id.layout_too_tPadrao) }
    val recyclerView by lazy { findViewById<RecyclerView>(R.id.cat_rvCategoriasDetalhes) }
    val progressBar by lazy { findViewById<ProgressBar>(R.id.cat_pbCategoriasDetalhes) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        //settings
        settingToolbar()
        var listMovies = getListMovies()

        if(listMovies != null){

            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

            var adapter = CategoryDetailAdapter(listMovies)

            recyclerView.adapter = adapter

        }
    }


    //settings
    fun settingToolbar(){
        toolbar.setTitle(getString(R.string.title_activity_categoria_detalhe))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_textcolor));

        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
    fun getListMovies():MutableList<Movie>?{

        val code : Int = intent.getIntExtra("code", 0)
        var title : String = intent.getStringExtra("title").toString();

        updateTitleToolbar(title)

        if(code != null && title != null){
            //var list:MutableList<Movie> = Gson().fromJson(bundle, MutableList<Movie>)

            var listMovies = mutableListOf<Movie>()
            var titleCategory = title;
            var codeCategory = code;
            for (i in 1 .. 10 ){
                listMovies.add(Movie(i, "Filme ${i}", "Filme para a categoria ${titleCategory}", "https://picsum.photos/800/600?random=${codeCategory}"))
            }

            return listMovies;
        }

        Toast.makeText(
            this,
            "Não foi possível obter a lista com os filmes! Dados não encontrados.",
            Toast.LENGTH_SHORT
        ).show()

        return null;
    }


    //overrides
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    fun updateTitleToolbar(newTitle:String){
        toolbar.setTitle(newTitle)
    }

}