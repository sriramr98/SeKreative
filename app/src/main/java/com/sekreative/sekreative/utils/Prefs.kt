package com.sekreative.sekreative.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.*

object Prefs {

    private lateinit var mPrefs: SharedPreferences
    private const val KEY_AUTH_TOKEN = "key-auth-token"

    fun init(context: Context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setAuthToken(token: String = UUID.randomUUID().toString()) {
        mPrefs.edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
    }

    fun getAuthToken() = mPrefs.getString(KEY_AUTH_TOKEN, "")
}