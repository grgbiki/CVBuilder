package com.bikram.cvbuilder.ui.aboutme

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bikram.cvbuilder.models.DataRepository
import com.bikram.cvbuilder.models.Education

class AboutMeViewModel : ViewModel() {
    private var _education: LiveData<List<Education>>? = null

    var educations: LiveData<List<Education>>? = null

    fun getEducation(context: Context): LiveData<List<Education>> {
        if (educations == null) {
            val userData = DataRepository(context).getUserDataFromJson()

            _education = MutableLiveData<List<Education>>().apply {
                value = userData.educations
            }
            educations = _education
        }
        return educations!!
    }
}