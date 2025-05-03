package com.example.demo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LanguageLearningActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var btnLearningMode: Button
    private lateinit var btnEmergencyMode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve preferred language from shared preferences
        val prefs: SharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE)
        val selectedLanguage = prefs.getString("selectedLanguage", "en") ?: "en"
        LocaleHelper.setLocale(this, selectedLanguage)

        setContentView(R.layout.activity_language_learning)

        // Initialize UI elements
        tvWelcome = findViewById(R.id.tv_welcome_message)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.nav_view)
        btnLearningMode = findViewById(R.id.btn_learning_mode)
        btnEmergencyMode = findViewById(R.id.btn_emergency_mode)

        // Set button labels from strings.xml
        btnLearningMode.text = getString(R.string.learning_mode)
        btnEmergencyMode.text = getString(R.string.emergency_mode)

        // Display welcome message with user's name
        val username = prefs.getString("username", null) ?: getString(R.string.demouser)
        tvWelcome.text = getString(R.string.welcome_message, username)

        // Setup toolbar and navigation drawer
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.menu)
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        // Handle navigation menu item selection
        navView.setNavigationItemSelectedListener { menuItem ->
            handleMenuItemClick(menuItem)
            drawerLayout.close()
            true
        }

        // Launch language selection activity
        btnLearningMode.setOnClickListener {
            val intent = Intent(this, WhichLangActivity::class.java)
            startActivity(intent)
        }

        // Placeholder for emergency mode
        btnEmergencyMode.setOnClickListener {
            Toast.makeText(this, getString(R.string.emergency_coming_soon), Toast.LENGTH_SHORT).show()
        }
    }

    // Handle item clicks from the navigation drawer
    private fun handleMenuItemClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Tracking progress (to be implemented)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_change_language -> {
                showLanguageChangeDialog()
            }
            R.id.nav_logout -> {
                getSharedPreferences("MyApp", MODE_PRIVATE).edit { clear() }
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                finishAffinity()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    // Show language selection dialog
    private fun showLanguageChangeDialog() {
        val languages = arrayOf("English", "हिन्दी")
        val codes = arrayOf("en", "hi")

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.change_language))
        builder.setItems(languages) { _, which ->
            val selectedCode = codes[which]
            val prefs = getSharedPreferences("MyApp", MODE_PRIVATE)
            prefs.edit { putString("selectedLanguage", selectedCode) }
            LocaleHelper.setLocale(this, selectedCode)

            Toast.makeText(this, getString(R.string.language_changed), Toast.LENGTH_SHORT).show()
            recreate()
        }
        builder.show()
    }

    // Apply locale based on saved preference before attaching context
    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("MyApp", MODE_PRIVATE)
        val langCode = prefs.getString("selectedLanguage", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, langCode)
        super.attachBaseContext(context)
    }
}
