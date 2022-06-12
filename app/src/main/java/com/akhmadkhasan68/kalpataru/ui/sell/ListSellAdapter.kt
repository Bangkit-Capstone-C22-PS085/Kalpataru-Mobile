package com.akhmadkhasan68.kalpataru.ui.sell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataCart
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class ListSellAdapter(private val listData : List<DataCart>) : RecyclerView.Adapter<ListSellAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataCart)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTrashName : TextView = itemView.findViewById(R.id.tv_trash_name)
        val tvQuantity : TextView = itemView.findViewById(R.id.tv_quantity)
        val tvNominal : TextView = itemView.findViewById(R.id.tv_nominal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSellAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_sell, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSellAdapter.ListViewHolder, position: Int) {
        val (_, quantity, _, subtotal, _, _, _, trash) = listData[position]
        holder.tvTrashName.text = trash?.jenis
        holder.tvNominal.text = "Rp. ${subtotal.toString()}"
        holder.tvQuantity.text = "${quantity.toString()} Kg"
    }

    override fun getItemCount(): Int = listData.size
}