package com.dhgrupo5.popfilm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dhgrupo5.popfilm.R

class AboutChannelFragment : Fragment() {

    companion object {

        const val KEY = "AboutChannelFragment_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_about_channel,
            container,
            false
        )
    }
}