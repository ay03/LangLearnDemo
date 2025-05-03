package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Data model for representing a language
data class Language(val name: String, val code: String)

// RecyclerView Adapter for displaying a list of languages
class LanguageAdapter(
    private val languages: List<Language>,             // List of available languages
    private val onItemClick: (Language) -> Unit        // Callback for item selection
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    // ViewHolder holds reference to the TextView inside each list item
    class LanguageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val languageTextView: TextView = view.findViewById(R.id.tv_language)
    }

    // Inflate the layout for a single list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
        return LanguageViewHolder(view)
    }

    // Bind the data to each list item
    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.languageTextView.text = language.name

        // Handle click event for this items
        holder.itemView.setOnClickListener {
            onItemClick(language)
        }
    }

    // Return total number of list items
    override fun getItemCount(): Int = languages.size
}
