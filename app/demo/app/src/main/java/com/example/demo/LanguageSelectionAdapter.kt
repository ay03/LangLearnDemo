package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageSelectionAdapter(
    private val languages: List<LanguageOption>,
    private val onClick: (LanguageOption) -> Unit
) : RecyclerView.Adapter<LanguageSelectionAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language_selection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = languages[position]
        holder.text.text = holder.text.context.getString(item.nameResId)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = languages.size
}
