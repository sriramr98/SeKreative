package com.sekreative.sekreative

import android.app.Application
import com.sekreative.sekreative.utils.Prefs

class SeKreative: Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
    }
}