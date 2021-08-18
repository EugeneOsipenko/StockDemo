package com.eugeneosipenko.stockdemo.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eugeneosipenko.stockdemo.R
import com.eugeneosipenko.stockdemo.databinding.FragmentListBinding
import com.eugeneosipenko.stockdemo.ui.details.DetailsFragmentArgs
import com.eugeneosipenko.stockdemo.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockListFragment : Fragment() {

    private val viewModel: StockListViewModel by viewModels()

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: StockListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StockListAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.companies.collect { adapter.submitList(it) }
            }

            launch {
                viewModel.openCompanyDetails.collect { company ->
                    val action = StockListFragmentDirections.toDetails(company)
                    findNavController().navigate(action)
                }
            }
        }
    }
}
