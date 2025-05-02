package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SentenceAdapter(
    private val sentences: List<Sentence>,
    private val knownLangCode: String,
    private val onItemClick: (Sentence) -> Unit
) : RecyclerView.Adapter<SentenceAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sentenceText: TextView = view.findViewById(R.id.tv_sentence)
        init {
            view.setOnClickListener {
                onItemClick(sentences[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sentence, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sentence = sentences[position]
        val text = when (knownLangCode) {
            "hi" -> sentence.hindi
            "mr" -> sentence.marathi
            "gu" -> sentence.gujarati
            "ta" -> sentence.tamil
            "te" -> sentence.telugu
            "ml" -> sentence.malayalam
            "pa" -> sentence.punjabi
            "bn" -> sentence.bengali
            "as" -> sentence.assamese
            "kn" -> sentence.kannada
            else -> sentence.english
        }
        holder.sentenceText.text = text
    }

    override fun getItemCount(): Int = sentences.size
}
