package com.example.demo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.edit
import com.example.demo.Language
import com.example.demo.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var selectedLanguage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        // List of languages with their codes
        val languages = listOf(
            Language("English", "en"),
            Language("हिन्दी (Hindi)", "hi")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = LanguageAdapter(languages) { selectedLang ->
            selectedLanguage = selectedLang.code
            saveSelectedLanguage(selectedLang.code)
            goToTermsAndConditions()
        }
        recyclerView.adapter = adapter
    }

    private fun saveSelectedLanguage(language: String) {
        val prefs = getSharedPreferences("MyApp", MODE_PRIVATE)
        prefs.edit {
            putString("selectedLanguage", language)
        }
    }

    private fun goToTermsAndConditions() {
        val intent = Intent(this, TermsAndConditionsActivity::class.java)
        startActivity(intent)
    }
}
