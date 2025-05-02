package com.example.demo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import androidx.core.content.edit

class WhichLangActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var recyclerView: RecyclerView

    // ⬇️ Add this to apply language before onCreate
    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("MyApp", MODE_PRIVATE)
        val langCode = prefs.getString("selectedLanguage", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, langCode)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_which_lang)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.nav_view)
        recyclerView = findViewById(R.id.recycler_languages)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.menu)
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            handleMenuItemClick(menuItem)
            drawerLayout.close()
            true
        }

        val languageOptions = listOf(
            LanguageOption(R.string.language_hindi, "hi"),
            LanguageOption(R.string.language_marathi, "mr"),
            LanguageOption(R.string.language_kannada, "kn")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LanguageSelectionAdapter(languageOptions) { selected ->
            val knownLang = LocaleHelper.getLocale(this)
            val learnLang = selected.code

            if (knownLang == learnLang) {
                // Show a Toast in the known/default language
                val message = getString(R.string.learn_same_lang_error)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SentenceListActivity::class.java)
                intent.putExtra("learnLangCode", learnLang)
                startActivity(intent)
            }
        }

    }

    private fun handleMenuItemClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Tracking progress (to be implemented)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_change_language -> {
                showLanguageChangeDialog()
            }
            R.id.nav_logout -> {
                getSharedPreferences("MyApp", MODE_PRIVATE).edit() { clear() }
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }
        }
    }

    private fun showLanguageChangeDialog() {
        val languages = arrayOf(
            "English", "हिन्दी"
        )

        val codes = arrayOf(
            "en", "hi"
        )


        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.change_language))
        builder.setItems(languages) { _, which ->
            val selectedCode = codes[which]
            getSharedPreferences("MyApp", MODE_PRIVATE).edit() {
                putString("selectedLanguage", selectedCode)
            }
            LocaleHelper.setLocale(this, selectedCode)
            Toast.makeText(this, getString(R.string.language_changed), Toast.LENGTH_SHORT).show()
            recreate()
        }
        builder.show()
    }
}
