package com.dhgrupo5.popfilm.ui.fragment

import android.os.Bundle
import com.dhgrupo5.popfilm.R
import com.dhgrupo5.popfilm.data.fixed.BusinessContactData
import com.dhgrupo5.popfilm.model.BusinessContact
import com.dhgrupo5.popfilm.ui.adapter.ListItemAdapter

class BusinessContactsFragment : FrameworkListFragment() {

    companion object {
        const val KEY = "BusinessContactsFragment_key"
    }

    override fun onActivityCreated(
        savedInstanceState: Bundle? ){
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.business_contacts_content_title ),
            subTitleText = getString( R.string.business_contacts_desc )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = BusinessContactData.getBusinessContacts(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    webUri = item.getWebUri(),
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.business_contact_toast_alert ),
                        (item as BusinessContact).place,
                        item.contact
                    )
                )
            }
        )
        initList( adapter = adapter )
    }
}