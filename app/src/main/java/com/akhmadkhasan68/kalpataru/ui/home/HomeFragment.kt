package com.akhmadkhasan68.kalpataru.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.databinding.FragmentHomeBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory

private val Context.dataStore by preferencesDataStore("settings")
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dataStore : DataStore<Preferences>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataStore = requireContext().dataStore
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvHome.layoutManager = LinearLayoutManager(activity)
        val homeAdapter = HomeAdapter()
        binding.rvHome.adapter = homeAdapter
        setupViewModel()

        return root
    }

    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[HomeViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
