package com.akhmadkhasan68.kalpataru.ui.history

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.DataTransactions
import com.akhmadkhasan68.kalpataru.databinding.FragmentHistoryBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory

private val Context.dataStore by preferencesDataStore("settings")
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var dataStore : DataStore<Preferences>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataStore = requireContext().dataStore
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupView()
        setupViewModel()

        return root
    }

    private fun setupViewModel() {
        historyViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[HistoryViewModel::class.java]

        historyViewModel.getUser().observe(viewLifecycleOwner, { user ->
            if(user.type.toString() == "MEMBER"){
                historyViewModel.getData()
            }else{
                historyViewModel.getDataOperatorTransaction()
            }
        })

        historyViewModel.data.observe(viewLifecycleOwner, {
            setHistoryData(it)
        })

        historyViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            showLoading(isLoading)
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    private fun setHistoryData(data: List<DataTransactions>) {
        if(data.isNotEmpty()){
            binding.rvHistory.visibility = View.VISIBLE

            if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.rvHistory.layoutManager = GridLayoutManager(context, 2)
            }else{
                binding.rvHistory.layoutManager = LinearLayoutManager(context)
            }

            val listHistoryAdapter = ListHistoryAdapter(data)
            binding.rvHistory.adapter = listHistoryAdapter

            listHistoryAdapter.setOnItemClickCallback(object: ListHistoryAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataTransactions) {
                    showSelectedData(data)
                }
            })
        }else{
            binding.rvHistory.visibility = View.INVISIBLE
        }
    }

    private fun showSelectedData(data: DataTransactions) {
        val intent = Intent(getContext(), HistoryDetailActivity::class.java)
        intent.putExtra(HistoryDetailActivity.DATA_DETAIL, data)
        startActivity(intent)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_all ->
                    if (checked) {
                        // Pirates are the best
                    }
                R.id.radio_finish ->
                    if (checked) {
                        // Ninjas rule
                    }
                R.id.radio_cancel ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }


    private fun setupView() {
        binding.btnFilter.setOnClickListener {
            toggleFilter()
        }

        binding.btnCancel.setOnClickListener {
            toggleFilter()
        }

        binding.btnApply.setOnClickListener {
            toggleFilter()
        }
    }

    private fun toggleFilter() {
        if(binding.filterForm.visibility == View.GONE){
            binding.filterForm.visibility = View.VISIBLE
        }else{
            binding.filterForm.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
