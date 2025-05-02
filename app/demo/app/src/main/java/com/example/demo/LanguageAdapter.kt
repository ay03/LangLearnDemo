package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Language(val name: String, val code: String)

// Modify your adapter to use this class
class LanguageAdapter(
    private val languages: List<Language>, // Use the Language data class
    private val onItemClick: (Language) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    class LanguageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val languageTextView: TextView = view.findViewById(R.id.tv_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.languageTextView.text = language.name

        holder.itemView.setOnClickListener {
            onItemClick(language)
        }
    }

    override fun getItemCount(): Int = languages.size
}


