package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for displaying a list of selectable languages
class LanguageSelectionAdapter(
    private val languages: List<LanguageOption>,           // List of language options
    private val onClick: (LanguageOption) -> Unit          // Callback when an item is selected
) : RecyclerView.Adapter<LanguageSelectionAdapter.ViewHolder>() {

    // ViewHolder representing each item view in the list
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv_language)
    }

    // Inflate the layout for a single language item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language_selection, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the item view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = languages[position]
        holder.text.text = holder.text.context.getString(item.nameResId)

        // Handle click on the item
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int = languages.size
}
