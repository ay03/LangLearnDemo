package com.example.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class SentenceListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_sentences)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve user's known language and selected target language
        val knownLang = LocaleHelper.getLocale(this)
        val learnLang = intent.getStringExtra("learnLangCode") ?: "en"

        // Load sentence data from local assets JSON
        val inputStream = assets.open("sentences.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<Map<String, List<Sentence>>>() {}.type
        val data = Gson().fromJson<Map<String, List<Sentence>>>(reader, type)
        val sentences = data["sentences"] ?: emptyList()

        // Set up RecyclerView with SentenceAdapter
        recyclerView.adapter = SentenceAdapter(sentences, knownLang) { sentence ->
            val intent = Intent(this, SentenceDetailActivity::class.java).apply {
                putExtra("sentenceId", sentence.id)
                putExtra("knownLangCode", knownLang)
                putExtra("learnLangCode", learnLang)
            }
            startActivity(intent)
        }
    }

    // Apply saved locale before context attaches to activity
    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("MyApp", MODE_PRIVATE)
        val langCode = prefs.getString("selectedLanguage", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, langCode)
        super.attachBaseContext(context)
    }
}
