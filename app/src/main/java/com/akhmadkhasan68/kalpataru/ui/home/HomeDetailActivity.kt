package com.akhmadkhasan68.kalpataru.ui.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DetailTransaction
import com.akhmadkhasan68.kalpataru.databinding.ActivityHomeDetailBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.history.ListTrashAdapter
import com.akhmadkhasan68.kalpataru.ui.main.MainActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class HomeDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeDetailBinding
    private lateinit var homeDetailViewModel: HomeDetailViewModel
    private lateinit var dataDetail : DataTransactions

    companion object{
        const val DATA_DETAIL = "DATA_DETAIL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataDetail = intent.getParcelableExtra<DataTransactions>(HomeDetailActivity.DATA_DETAIL) as DataTransactions

        setupViewModel()
        setupView()
        setupActions()
    }

    private fun setupViewModel() {
        homeDetailViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[HomeDetailViewModel::class.java]

        homeDetailViewModel.isLoading.observe(this, { isLoading ->
            showLoading(isLoading)
        })

        homeDetailViewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        homeDetailViewModel.isSuccess.observe(this, {
            if(it){
                startActivity(Intent(this@HomeDetailActivity, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun showLoading(loading: Boolean) {
        if(loading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    private fun setupActions() {
        binding.btnBuy.setOnClickListener {
            dataDetail.id?.let { it1 -> homeDetailViewModel.buyTransaction(it1) }
        }
    }

    private fun setupView() {
        binding.tvSellername.text = dataDetail.member?.member?.name
        binding.tvSelldate.text = dataDetail.createdAt
        binding.tvSelleraddress.text = dataDetail.member?.member?.address
        binding.tvNominal.text = dataDetail.total

        setUsersData(dataDetail.details)
    }

    private fun setUsersData(details: List<DetailTransaction>) {
        if(details.isNotEmpty()){
            if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.rvTrash.layoutManager = GridLayoutManager(this, 2)
            }else{
                binding.rvTrash.layoutManager = LinearLayoutManager(this)
            }

            val listUserAdapter = ListTrashAdapter(details)
            binding.rvTrash.adapter = listUserAdapter
        }
    }
}