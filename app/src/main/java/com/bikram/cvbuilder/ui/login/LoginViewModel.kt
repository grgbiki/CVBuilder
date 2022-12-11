package com.bikram.cvbuilder.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var prefs: SharedPreferences? = null
    private var _isLoggedIn: LiveData<Boolean>? = null
    var isLoggedIn: LiveData<Boolean>? = _isLoggedIn

    fun checkLoginStatus(context: Context) {
        prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        _isLoggedIn = MutableLiveData<Boolean>().apply {
            value = prefs!!.getBoolean("loggedIn", false)
        }
        isLoggedIn = _isLoggedIn
    }

    fun login(email: String, password: String): LiveData<Boolean> {
        val savedEmail = prefs!!.getString("email", "")
        val savedPassword = prefs!!.getString("password", "")

        if (email == savedEmail && password == savedPassword) {
            prefs!!.edit {
                putBoolean("loggedIn", true)
            }
            _isLoggedIn = MutableLiveData<Boolean>().apply {
                value = true
            }
            isLoggedIn = _isLoggedIn
            return isLoggedIn!!
        } else {
            _isLoggedIn = MutableLiveData<Boolean>().apply {
                value = false
            }
            isLoggedIn = _isLoggedIn
            return isLoggedIn!!
        }
    }

    fun register(email: String, password: String): LiveData<Boolean> {
        prefs!!.edit {
            putString("email", email)
            putString("password", password)
            putBoolean("loggedIn", true)
        }
        _isLoggedIn = MutableLiveData<Boolean>().apply {
            value = true
        }
        isLoggedIn = _isLoggedIn
        return isLoggedIn!!
    }

}