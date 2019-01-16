package com.sekreative.sekreative.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Prefs {

    private lateinit var mPrefs: SharedPreferences

    fun init(context: Context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }
}