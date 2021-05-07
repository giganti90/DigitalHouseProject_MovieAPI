package com.dhgrupo5.popfilm.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhgrupo5.popfilm.R
import kotlinx.android.synthetic.main.fragment_framework_list.*


abstract class FrameworkListFragment : Fragment() {

    companion object {

        private const val MARGIN_TO_SUB_TITLE = 5
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_framework_list,
            container,
            false
        )
    }


    protected fun setUiModel(
        titleText: String,
        subTitleText: String = "" ){

        tv_main_text.text = titleText
        tv_sub_title.text = subTitleText
        tv_sub_title.visibility = if( subTitleText.isEmpty() ){
                View.GONE
            }
            else{
                updateMainTextMargin()
                View.VISIBLE
            }
    }


    private fun updateMainTextMargin(){
        val tvTitleLayoutParams = (tv_main_text.layoutParams as LinearLayout.LayoutParams)
        tvTitleLayoutParams.bottomMargin = convertDpToPixels()
    }


    private fun convertDpToPixels() : Int {
        val scale = resources.displayMetrics.density
        return (MARGIN_TO_SUB_TITLE * scale + 0.5f).toInt()
    }


    protected fun <T : RecyclerView.ViewHolder> initList(
        adapter: RecyclerView.Adapter<T> ){

        val layoutManager = LinearLayoutManager( activity )
        rv_items.layoutManager = layoutManager

        rv_items.setHasFixedSize( true )
        rv_items.adapter = adapter
    }


    protected fun callExternalApp(
        webUri: Uri? = null,
        appUri: Uri? = null,
        failMessage: String ){

        val intentApp = getIntentUri( uri = appUri )
        val intentWeb = getIntentUri( uri = webUri )
        var intent: Intent? = null


        if( intentApp != null
            && intentApp.resolveActivity( activity!!.packageManager ) != null ){

            intent = intentApp
        }
        else if( intentWeb != null
            && intentWeb.resolveActivity( activity!!.packageManager ) != null ){

            intent = intentWeb
        }

        if( intent != null ){
            activity!!.startActivity( intent )
        }
        else{

            Toast
                .makeText(
                    activity,
                    failMessage,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

    private fun getIntentUri( uri: Uri? ) : Intent?
        = if( uri != null ){
            Intent(
                Intent.ACTION_VIEW,
                uri
            )
        }
        else{
            null
        }
}