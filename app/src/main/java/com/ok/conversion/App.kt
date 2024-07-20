package com.ok.conversion

import android.app.Application

class App : Application() {
    companion object {
        lateinit var userPreferences: UserPreferences
    }

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(this)
    }
}