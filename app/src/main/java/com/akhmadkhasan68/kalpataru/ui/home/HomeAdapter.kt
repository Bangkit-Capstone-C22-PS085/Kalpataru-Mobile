package com.akhmadkhasan68.kalpataru.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction
import com.akhmadkhasan68.kalpataru.ui.history.ListHistoryAdapter

class HomeAdapter(private val listData : List<DataTransactions>) : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTransactions)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvSellDate: TextView = itemView.findViewById(R.id.tv_selldate)
        val tvTotalWeight: TextView = itemView.findViewById(R.id.tv_total_weight)
        val tvTotalItem: TextView = itemView.findViewById(R.id.tv_total_item)
        val tvTotal: TextView = itemView.findViewById(R.id.tv_total)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ListViewHolder, position: Int) {
        val (createdAt, total, _, member, _, detail, _, _, status, _) = listData[position]
        Log.d("detail", listData[position].toString())
        holder.tvName.text = member?.member?.name
        holder.tvSellDate.text = member?.member?.address
        holder.tvTotalWeight.text = getTotalWeight(detail).toString()
        holder.tvTotalItem.text = detail?.size.toString()
        holder.tvTotal.text = total
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