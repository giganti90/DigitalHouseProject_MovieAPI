package com.dhgrupo5.popfilm.ui.fragment

import android.os.Bundle
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.data.fixed.SocialNetworksData
import com.dhgrupo5.popfilm.model.SocialNetwork
import com.dhgrupo5.popfilm.ui.adapter.ListItemAdapter

class SocialNetworksFragment : FrameworkListFragment() {

    companion object {

        const val KEY = "SocialNetworksFragment_key"
    }

    override fun onActivityCreated(
        savedInstanceState: Bundle? ){
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.social_networks_content_title )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = SocialNetworksData.getNetworks(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    webUri = item.getWebUri(),
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.social_network_toast_alert ),
                        (item as SocialNetwork).network
                    )
                )
            }
        )
        initList( adapter = adapter )
    }
}