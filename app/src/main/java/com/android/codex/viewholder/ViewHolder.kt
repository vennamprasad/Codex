package com.android.codex

import android.view.ViewGroup
import android.widget.TextView
import com.android.codex.data.Category
import smartadapter.viewholder.SmartViewHolder

class CategoryViewHolder(parentView: ViewGroup) :
    SmartViewHolder<Category>(parentView, R.layout.home_category_item) {
    private val title: TextView = itemView.findViewById(R.id.label_header)
    override fun bind(category: Category) {
        title.text=category.title
    }

    override fun unbind() {
        title.text=""
    }
}