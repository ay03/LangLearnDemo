package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RecyclerView adapter to display a list of sentences in the user's known language
class SentenceAdapter(
    private val sentences: List<Sentence>,             // List of sentence data
    private val knownLangCode: String,                 // Language code selected by the user
    private val onItemClick: (Sentence) -> Unit        // Callback when a sentence is selected
) : RecyclerView.Adapter<SentenceAdapter.ViewHolder>() {

    // ViewHolder holds reference to the sentence text view
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sentenceText: TextView = view.findViewById(R.id.tv_sentence)

        init {
            // Handle item click by invoking the callback
            view.setOnClickListener {
                onItemClick(sentences[adapterPosition])
            }
        }
    }

    // Inflate layout for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sentence, parent, false)
        return ViewHolder(view)
    }

    // Bind sentence text based on the known language
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sentence = sentences[position]
        val text = when (knownLangCode) {
            "hi" -> sentence.hindi
            "mr" -> sentence.marathi
            "kn" -> sentence.kannada
            else -> sentence.english
        }
        holder.sentenceText.text = text
    }

    // Return total number of sentences
    override fun getItemCount(): Int = sentences.size
}
