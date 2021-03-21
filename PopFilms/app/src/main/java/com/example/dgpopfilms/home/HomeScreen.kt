package com.example.dgpopfilms.home

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dgpopfilms.R
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import java.lang.ref.WeakReference

class HomeScreen : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler(){
        recyclerView = findViewById<RecyclerView>(R.id.rv_parent)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeScreen,
                LinearLayoutManager.VERTICAL, false)
            adapter = ParentAdapter(Parent
                .getParents(40))
        }

    }
}
