package com.akhmadkhasan68.kalpataru.ui.sell

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataCart
import com.akhmadkhasan68.kalpataru.databinding.FragmentSellBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory

private val Context.dataStore by preferencesDataStore("settings")
class SellFragment : Fragment() {
    private var _binding: FragmentSellBinding? = null
    private var sellViewModel : SellViewModel? = null
    private lateinit var dataStore : DataStore<Preferences>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataStore = requireContext().dataStore
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModel()
        setupActions()

        return root
    }

    private fun setupViewModel() {
        sellViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SellViewModel::class.java]

        sellViewModel?.getData()

        sellViewModel?.data?.observe(viewLifecycleOwner, {
            setSellData(it)

            binding.tvNominal.text =  "Rp. ${countNonimal(it).toString()}"
        })

        sellViewModel?.isLoading?.observe(viewLifecycleOwner, { isLoading ->
            showLoading(isLoading)
        })

        sellViewModel?.message?.observe(viewLifecycleOwner, {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

        sellViewModel?.isError?.observe(viewLifecycleOwner, {
            if(!it){
                Toast.makeText(context, "Berhasil melakukan penjualan", Toast.LENGTH_SHORT).show()
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
        binding.btnSell.setOnClickListener {
            sellViewModel?.createTransaction()
        }
    }

    private fun countNonimal(data: List<DataCart>?): Int {
        var total = 0
        data?.forEach {
            total = total + it?.subtotal!!
        }
        return total
    }

    private fun setSellData(data: List<DataCart>) {
        if(data.isNotEmpty()){
            binding.rvSell.visibility = View.VISIBLE

            if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.rvSell.layoutManager = GridLayoutManager(context, 2)
            }else{
                binding.rvSell.layoutManager = LinearLayoutManager(context)
            }

            val listSellAdapter = ListSellAdapter(data)
            binding.rvSell.adapter = listSellAdapter
        }else{
            binding.rvSell.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sellViewModel = null
    }
}
