package com.akhmadkhasan68.kalpataru.ui.history

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction
import com.akhmadkhasan68.kalpataru.databinding.ActivityHistoryDetailBinding

class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryDetailBinding

    companion object{
        const val DATA_DETAIL = "data_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.hide()

        val dataDetail = intent.getParcelableExtra<DataTransactions>(DATA_DETAIL) as DataTransactions
        binding.tvStatus.text = dataDetail.status
        binding.tvSelldate.text = dataDetail.createdAt
        binding.tvNominal.text = dataDetail.total

        setUsersData(dataDetail.details)
    }

    private fun setUsersData(data: List<DetailTransaction>) {
        if(data.isNotEmpty()){
            if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.rvTrash.layoutManager = GridLayoutManager(this, 2)
            }else{
                binding.rvTrash.layoutManager = LinearLayoutManager(this)
            }

            val listUserAdapter = ListTrashAdapter(data)
            binding.rvTrash.adapter = listUserAdapter
        }
    }


}