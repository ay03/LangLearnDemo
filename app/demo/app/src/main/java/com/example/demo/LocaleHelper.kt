package com.example.demo

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

private const val PREFS_NAME = "MyApp"
private const val SELECTED_LANGUAGE = "selectedLanguage"

/**
 * Sets the application's locale based on the given language code.
 */
fun setLocale(context: Context, language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    config.setLayoutDirection(locale)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.createConfigurationContext(config)
    } else {
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        context
    }
}

/**
 * Retrieves the currently saved language code from shared preferences.
 */
fun getLocale(context: Context): String {
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return prefs.getString(SELECTED_LANGUAGE, "en") ?: "en"
}

/**
 * Saves the chosen language to shared preferences.
 */
private fun saveLanguageToPrefs(context: Context, language: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit().putString(SELECTED_LANGUAGE, language).apply()
}

/**
 * Updates the app's configuration with the selected language.
 */
private fun updateResources(context: Context, language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setLocaleForApi24(config, locale, context)
    } else {
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        ContextWrapper(context)
    }
}

/**
 * Handles locale update for API 24 and above.
 */
@TargetApi(Build.VERSION_CODES.N)
private fun setLocaleForApi24(config: Configuration, locale: Locale, context: Context): Context {
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return context.createConfigurationContext(config)
}
