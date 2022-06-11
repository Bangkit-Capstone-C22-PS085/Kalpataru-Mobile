package com.akhmadkhasan68.kalpataru.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction

class ListTrashAdapter(private val detailTransaction : List<DetailTransaction>) : RecyclerView.Adapter<ListTrashAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTrashName: TextView = itemView.findViewById(R.id.tv_trash_name)
        var tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        var tvSubtotal: TextView = itemView.findViewById(R.id.tv_subtotal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListTrashAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_trash, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (_, _, quantity, subtotal, _, _, _, trash) = detailTransaction[position]
        holder.tvTrashName.text = trash?.jenis
        holder.tvQuantity.text = "${quantity.toString()} Kg"
        holder.tvSubtotal.text = subtotal.toString()
    }

    override fun getItemCount(): Int = detailTransaction.size

}