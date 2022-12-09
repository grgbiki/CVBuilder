package com.bikram.cvbuilder.ui.work

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bikram.cvbuilder.models.DataRepository
import com.bikram.cvbuilder.models.Experience

class WorkViewModel : ViewModel() {
    private var _experience: LiveData<List<Experience>>? = null

    var experiences: LiveData<List<Experience>>? = null

    fun getExperience(context: Context): LiveData<List<Experience>> {
        if (experiences == null) {
            val userData = DataRepository(context).getUserDataFromJson()

            _experience = MutableLiveData<List<Experience>>().apply {
                value = userData.experiences
            }
            experiences = _experience
        }
        return experiences!!
    }
}