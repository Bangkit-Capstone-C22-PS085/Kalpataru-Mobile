package com.akhmadkhasan68.kalpataru.ui.history

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.databinding.FragmentHistoryBinding
import com.akhmadkhasan68.kalpataru.ui.sell.ListSellAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvHistory.layoutManager = LinearLayoutManager(activity)
        val listHistoryAdapter = ListHistoryAdapter()
        binding.rvHistory.adapter = listHistoryAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
