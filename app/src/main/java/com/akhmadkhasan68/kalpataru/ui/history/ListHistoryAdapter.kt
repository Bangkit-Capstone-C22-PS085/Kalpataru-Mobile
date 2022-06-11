package com.akhmadkhasan68.kalpataru.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction

class ListHistoryAdapter(private val listData : List<DataTransactions>) : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTransactions)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        var tvSellDate: TextView = itemView.findViewById(R.id.tv_selldate)
        var tvTotalWeight: TextView = itemView.findViewById(R.id.tv_total_weight)
        var tvTotalItem: TextView = itemView.findViewById(R.id.tv_total_item)
        var tvNominal: TextView = itemView.findViewById(R.id.tv_nominal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHistoryAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListHistoryAdapter.ListViewHolder, position: Int) {
        val (createdAt, total, _, _, _, detail, _, _, status, _) = listData[position]
        holder.tvStatus.text = status
        holder.tvSellDate.text = createdAt
        holder.tvTotalWeight.text = getTotalWeight(detail).toString()
        holder.tvTotalItem.text = detail?.size.toString()
        holder.tvNominal.text = total
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listData[holder.adapterPosition])}
    }

    private fun getTotalWeight(detail: List<DetailTransaction?>?): Int{
        var total = 0
        detail?.forEach { item ->
            total = total + item?.quantity!!
        }

        return total
    }

    override fun getItemCount(): Int = listData.size
}