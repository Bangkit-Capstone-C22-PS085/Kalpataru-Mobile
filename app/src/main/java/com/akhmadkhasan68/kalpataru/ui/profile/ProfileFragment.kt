package com.akhmadkhasan68.kalpataru.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.databinding.FragmentProfileBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory

private val Context.dataStore by preferencesDataStore("settings")
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileViewModel: ProfileViewModel
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModel()
        setupActions()

        return root
    }

    private fun setupActions() {
        binding.addressSection.setOnClickListener {

        }

        binding.logoutSection.setOnClickListener {
            profileViewModel.logout()
        }
    }

    private fun setupViewModel() {
        profileViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[ProfileViewModel::class.java]

        profileViewModel.getUser()

        profileViewModel.userDetail.observe(viewLifecycleOwner, {
            binding.textView.text = it.member?.name
            binding.tvProfilestatus.text = it.type?.trim()
            binding.point.text = it.member?.points.toString()
            binding.balance.text = it.member?.balance.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}