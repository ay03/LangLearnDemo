package com.example.demo

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileNotFoundException
import java.io.InputStreamReader

class TermsAndConditionsActivity : AppCompatActivity() {

    private lateinit var tvTerms: TextView
    private lateinit var btnToggleLanguage: Button
    private lateinit var checkboxAcceptTerms: CheckBox
    private lateinit var btnProceed: Button

    // Variable to hold translations map
    private var translations: Map<String, Map<String, String>> = mapOf()
    private var selectedLanguage: String = "en"  // Default language

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        // Initialize views
        tvTerms = findViewById(R.id.tv_terms)
        btnToggleLanguage = findViewById(R.id.btn_toggle_language)
        checkboxAcceptTerms = findViewById(R.id.checkbox_accept_terms)
        btnProceed = findViewById(R.id.btn_proceed)

        // Load translations from JSON
        loadTranslations()

        // Get the selected language from SharedPreferences
        selectedLanguage = getSelectedLanguage()

        // Set the terms text based on the selected language
        updateTermsText()

        // Toggle between selected language and English
        btnToggleLanguage.setOnClickListener {
            selectedLanguage = if (selectedLanguage == "en") {
                "hi" // Toggle to Hindi
            } else {
                "en" // Toggle back to English
            }
            updateTermsText()  // Update the text based on the language toggle
        }

        // Enable the proceed button when the checkbox is checked
        checkboxAcceptTerms.setOnCheckedChangeListener { _, isChecked ->
            btnProceed.isEnabled = isChecked
        }

        // Handle the Proceed button click
        btnProceed.setOnClickListener {
            val intent = Intent(this, LanguageLearningActivity::class.java)
            startActivity(intent)
        }
    }

    // Method to load translations from JSON file in assets
    private fun loadTranslations() {
        try {
            val assetManager = assets
            val inputStream = assetManager.open("translations.json")  // Change to translations.json
            val reader = InputStreamReader(inputStream)

            val type = object : TypeToken<Map<String, Map<String, String>>>() {}.type
            translations = Gson().fromJson(reader, type)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            // Handle file not found error here, e.g., show a Toast or log the error
        }
    }


    // Method to get the selected language from SharedPreferences
    private fun getSelectedLanguage(): String {
        val prefs = getSharedPreferences("MyApp", MODE_PRIVATE)
        return prefs.getString("selectedLanguage", "en") ?: "en"
    }

    // Method to update the terms and conditions text and UI elements based on the selected language
    private fun updateTermsText() {
        val termsText = translations[selectedLanguage]?.get("terms_and_conditions")
            ?: translations["en"]?.get("terms_and_conditions")  // Default to English if not found

        tvTerms.text = termsText

        // Update other UI elements dynamically
        btnToggleLanguage.text = translations[selectedLanguage]?.get("toggle_language")
            ?: translations["en"]?.get("toggle_language")
        checkboxAcceptTerms.text = translations[selectedLanguage]?.get("agree_terms")
            ?: translations["en"]?.get("agree_terms")
        btnProceed.text = translations[selectedLanguage]?.get("proceed")
            ?: translations["en"]?.get("proceed")
    }
}
