package com.bikram.cvbuilder.ui.aboutme

import android.content.Context
import android.content.SharedPreferences
import android.telephony.CellSignalStrength
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bikram.cvbuilder.models.DataRepository
import com.bikram.cvbuilder.models.Education

class AboutMeViewModel : ViewModel() {
    private var prefs: SharedPreferences? = null
    private var _education: LiveData<List<Education>>? = null
    var educations: LiveData<List<Education>>? = null
    private var _strengths: LiveData<Set<String>>? = null
    var strengths: LiveData<Set<String>>? = null
    private var _weakness: LiveData<Set<String>>? = null
    var weakness: LiveData<Set<String>>? = null

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

    fun getStrengths(context: Context): LiveData<Set<String>> {
        if (prefs == null) {
            prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        }
        val hashSet: MutableSet<String> = prefs!!.getStringSet("strengths", HashSet<String>())!!
        _strengths = MutableLiveData<Set<String>>().apply {
            value = hashSet
        }
        strengths = _strengths
        return strengths!!
    }

    fun addStrengths(context: Context, updatedStrengths: Set<String>) {
        if (prefs == null) {
            prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        }
        prefs!!.edit {
            putStringSet("strengths", updatedStrengths)
        }
        _strengths = MutableLiveData<Set<String>>().apply {
            value = updatedStrengths
        }
        strengths = _strengths
    }

    fun getWeakness(context: Context): LiveData<Set<String>> {
        if (prefs == null) {
            prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        }
        val hashSet: MutableSet<String> = prefs!!.getStringSet("weakness", HashSet<String>())!!
        _weakness = MutableLiveData<Set<String>>().apply {
            value = hashSet
        }
        weakness = _weakness
        return weakness!!
    }

    fun addWeakness(context: Context, updatedWeakness: Set<String>) {
        if (prefs == null) {
            prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        }
        prefs!!.edit {
            putStringSet("weakness", updatedWeakness)
        }
        _weakness = MutableLiveData<Set<String>>().apply {
            value = updatedWeakness
        }
        weakness = _weakness
    }
}