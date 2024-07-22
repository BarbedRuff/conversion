package com.ok.conversion

import android.app.Application
import com.ok.conversion.di.component.ApplicationComponent
import com.ok.conversion.di.component.DaggerApplicationComponent

class App : Application() {
    companion object {
        lateinit var userPreferences: UserPreferences
        val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(this)
    }
}