package com.example.demo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var selectedLanguage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        // Define supported languages and their locale codes
        val languages = listOf(
            Language("English", "en"),
            Language("हिन्दी (Hindi)", "hi")
        )

        // Set up RecyclerView with vertical layout
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Handle language selection from the adapter
        val adapter = LanguageAdapter(languages) { selectedLang ->
            selectedLanguage = selectedLang.code
            saveSelectedLanguage(selectedLang.code)
            goToTermsAndConditions()
        }

        recyclerView.adapter = adapter
    }

    // Save the selected language to shared preferences
    private fun saveSelectedLanguage(langCode: String) {
        val prefs: SharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE)
        prefs.edit {
            putString("selectedLanguage", langCode)
        }
    }

    // Navigate to the Terms and Conditions screen
    private fun goToTermsAndConditions() {
        val intent = Intent(this, TermsAndConditionsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
