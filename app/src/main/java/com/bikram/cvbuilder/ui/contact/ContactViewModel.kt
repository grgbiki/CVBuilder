package com.bikram.cvbuilder.ui.contact

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bikram.cvbuilder.models.Contact
import com.bikram.cvbuilder.models.DataRepository

class ContactViewModel : ViewModel() {
    private var _contact: LiveData<List<Contact>>? = null

    var contacts: LiveData<List<Contact>>? = null

    fun getContact(context: Context): LiveData<List<Contact>> {
        if (contacts == null) {
            val userData = DataRepository(context).getUserDataFromJson()

            _contact = MutableLiveData<List<Contact>>().apply {
                value = userData.contacts
            }
            contacts = _contact
        }
        return contacts!!
    }
}