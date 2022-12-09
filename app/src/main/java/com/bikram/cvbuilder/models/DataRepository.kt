package com.bikram.cvbuilder.models

import android.content.Context
import android.util.Log
import com.bikram.cvbuilder.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class DataRepository(private val context: Context) {
    private var userData: UserData? = null

    fun getUserDataFromJson(): UserData {
        if (userData == null) {
            val objectArrayString: String =
                context.resources.openRawResource(R.raw.user_data).bufferedReader()
                    .use { it.readText() }
            userData = Gson().fromJson(objectArrayString, UserData::class.java)
        }
        return userData!!
    }
}