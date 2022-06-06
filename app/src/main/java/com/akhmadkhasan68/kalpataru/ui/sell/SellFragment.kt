package com.akhmadkhasan68.kalpataru.ui.sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.databinding.FragmentSellBinding

class SellFragment : Fragment() {

    private var _binding: FragmentSellBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sellViewModel =
            ViewModelProvider(this).get(SellViewModel::class.java)

        _binding = FragmentSellBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSell
        sellViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
