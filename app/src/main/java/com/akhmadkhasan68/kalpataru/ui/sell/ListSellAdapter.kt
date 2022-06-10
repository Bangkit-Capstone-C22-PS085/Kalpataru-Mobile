package com.akhmadkhasan68.kalpataru.ui.sell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class ListSellAdapter : RecyclerView.Adapter<ListSellAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSellAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_sell, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSellAdapter.ListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 15
}