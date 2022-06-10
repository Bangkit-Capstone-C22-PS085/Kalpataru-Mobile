package com.akhmadkhasan68.kalpataru.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.ui.history.ListHistoryAdapter

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 15
}