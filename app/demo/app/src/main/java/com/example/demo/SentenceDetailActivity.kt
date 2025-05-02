package com.example.demo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class SentenceDetailActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("MyApp", MODE_PRIVATE)
        val langCode = prefs.getString("selectedLanguage", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, langCode)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence_detail)

        val tvKnown = findViewById<TextView>(R.id.tv_known_lang)
        val tvLearn = findViewById<TextView>(R.id.tv_learn_lang)
        val tvPhonetic = findViewById<TextView>(R.id.tv_phonetic)

        val sentenceId = intent.getIntExtra("sentenceId", 0)
        val knownLang = intent.getStringExtra("knownLangCode") ?: "en"
        val learnLang = intent.getStringExtra("learnLangCode") ?: "hi"

        val btnRecord = findViewById<Button>(R.id.btn_record)
        btnRecord.setOnClickListener {
            Toast.makeText(this, getString(R.string.to_be_implemented), Toast.LENGTH_SHORT).show()
        }

        try {
            val inputStream = assets.open("sentences.json")
            val reader = InputStreamReader(inputStream)
            val type = object : TypeToken<Map<String, List<Sentence>>>() {}.type
            val data = Gson().fromJson<Map<String, List<Sentence>>>(reader, type)
            val sentence = data["sentences"]?.find { it.id == sentenceId }

            if (sentence != null) {
                val knownText = sentence.getField(knownLang) ?: "-"
                val learnText = sentence.getField(learnLang) ?: "-"
                val phonetic = getPhonetic(sentence, knownLang, learnLang) ?: getString(R.string.phonetic_not_available)

                tvKnown.text = knownText
                tvLearn.text = learnText
                tvPhonetic.text = phonetic
            } else {
                tvKnown.text = "-"
                tvLearn.text = "-"
                tvPhonetic.text = getString(R.string.phonetic_not_available)
            }

        } catch (e: Exception) {
            tvKnown.text = "-"
            tvLearn.text = "-"
            tvPhonetic.text = getString(R.string.phonetic_not_available)
            e.printStackTrace()
        }
    }

    private fun Sentence.getField(langCode: String): String? {
        return when (langCode) {
            "hi" -> hindi
            "mr" -> marathi
            "kn" -> kannada
            else -> english
        }
    }

    private fun getPhonetic(sentence: Sentence, knownLang: String, learnLang: String): String? {
        return when {
            knownLang == "en" && learnLang == "hi" -> sentence.phonetic_from_english_for_hindi
            knownLang == "en" && learnLang == "kn" -> sentence.phonetic_from_english_for_kannada
            knownLang == "en" && learnLang == "mr" -> sentence.phonetic_from_english_for_marathi
            knownLang == "hi" && learnLang == "kn" -> sentence.phonetic_from_hindi_for_kannada
            knownLang == "hi" && learnLang == "mr" -> sentence.phonetic_from_hindi_for_marathi
            else -> null
        }
    }
}
