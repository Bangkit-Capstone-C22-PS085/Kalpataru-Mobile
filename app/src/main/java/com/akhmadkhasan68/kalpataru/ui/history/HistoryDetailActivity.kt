package com.akhmadkhasan68.kalpataru.ui.history

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction
import com.akhmadkhasan68.kalpataru.databinding.ActivityHistoryDetailBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.main.MainActivity

private val Context.dataStore by preferencesDataStore("settings")
class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryDetailBinding
    private lateinit var historyDetailViewModel: HistoryDetailViewModel
    private lateinit var dataDetail : DataTransactions

    companion object{
        const val DATA_DETAIL = "data_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataDetail = intent.getParcelableExtra<DataTransactions>(DATA_DETAIL) as DataTransactions

        setupViewModel()
        setupView()
        setupActions()
    }

    private fun setupActions() {
        binding.btnComplete.setOnClickListener {
            dataDetail.id?.let { it1 -> historyDetailViewModel.completeTransaction(it1) }
        }
    }

    private fun setupViewModel() {
        historyDetailViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[HistoryDetailViewModel::class.java]

        historyDetailViewModel.getUser().observe(this, {
            if(it.type.toString() == "MEMBER" && dataDetail.status.toString() == "WAITING"){
                binding.btnComplete.visibility = View.VISIBLE
            }else{
                binding.btnComplete.visibility = View.GONE
            }
        })

        historyDetailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        historyDetailViewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        historyDetailViewModel.isSuccess.observe(this, {
            startActivity(Intent(this@HistoryDetailActivity, MainActivity::class.java))
            finish()
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    private fun setupView() {
        supportActionBar?.hide()

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