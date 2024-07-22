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

    var basedCode: String
        get() = sharedPreferences.getString("Base code", "USD")!!
        set(value) {
            preferencesEditor.putString("Base code", value).apply()
        }

    var targetCode: String
        get() = sharedPreferences.getString("Target code", "RUB")!!
        set(value) {
            preferencesEditor.putString("Targte code", value).apply()
        }
}