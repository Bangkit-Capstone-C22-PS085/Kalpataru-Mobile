package com.akhmadkhasan68.kalpataru.ui.sell

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akhmadkhasan68.kalpataru.R
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

        binding.rvSell.layoutManager = LinearLayoutManager(activity)
        val listSellAdapter = ListSellAdapter()
        binding.rvSell.adapter = listSellAdapter

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sell_menu, menu)
    }
}
