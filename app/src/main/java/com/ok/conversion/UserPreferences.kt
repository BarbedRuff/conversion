package com.ok.conversion

import android.content.Context

class UserPreferences(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("User preferences", Context.MODE_PRIVATE)

    private val preferencesEditor by lazy {
        sharedPreferences.edit()
    }

    var baseLanguage: String
        get() = sharedPreferences.getString("Base language", "")!!
        set(value) {
            preferencesEditor.putString("Base language", value).apply()
        }

    var baseTheme: String
        get() = sharedPreferences.getString("Base theme", "")!!
        set(value) {
            preferencesEditor.putString("Base theme", value).apply()
        }
}