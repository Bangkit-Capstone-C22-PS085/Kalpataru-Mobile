package com.akhmadkhasan68.kalpataru.ui.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.User
import com.akhmadkhasan68.kalpataru.databinding.FragmentHomeBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory

private val Context.dataStore by preferencesDataStore("settings")
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dataStore : DataStore<Preferences>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataStore = requireContext().dataStore
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModel()

        return root
    }

    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[HomeViewModel::class.java]

        homeViewModel.getUser()

        homeViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            showLoading(isLoading)
        })

        homeViewModel.userDetail.observe(viewLifecycleOwner, {
            setupView(it)
        })

        homeViewModel.dataTransaction.observe(viewLifecycleOwner, {
            setTransactionData(it)
        })
    }

    private fun setTransactionData(data: List<DataTransactions>) {
        if(data.isNotEmpty()){
            if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.rvHome.layoutManager = GridLayoutManager(context, 2)
            }else{
                binding.rvHome.layoutManager = LinearLayoutManager(context)
            }

            val listHistoryAdapter = HomeAdapter(data)
            binding.rvHome.adapter = listHistoryAdapter

            listHistoryAdapter.setOnItemClickCallback(object: HomeAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataTransactions) {
                    showSelectedData(data)
                }
            })
        }else{
            binding.rvHome.visibility = View.INVISIBLE
        }
    }

    private fun showSelectedData(data: DataTransactions) {
        val intent = Intent(getContext(), HomeDetailActivity::class.java)
        intent.putExtra(HomeDetailActivity.DATA_DETAIL, data)
        startActivity(intent)
    }

    private fun setupView(it: User) {
        binding.tvAccount.text = it.username

        if(it.type.toString() == "OPERATOR") {
            homeViewModel.getDataTransaction()
            binding.tvInfo1.visibility = View.VISIBLE
            binding.rvHome.visibility = View.VISIBLE
            binding.tvDescription.visibility = View.VISIBLE
        } else {
            binding.tvCsoon.visibility = View.VISIBLE
        }
    }

    private fun showLoading(loading: Boolean) {
        if(loading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
