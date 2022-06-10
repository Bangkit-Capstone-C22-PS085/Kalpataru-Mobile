package com.akhmadkhasan68.kalpataru.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R

class ListHistoryAdapter : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHistoryAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListHistoryAdapter.ListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 15
}