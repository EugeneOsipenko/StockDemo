package com.eugeneosipenko.stockdemo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.eugeneosipenko.stockdemo.databinding.FragmentDetailsBinding
import com.eugeneosipenko.stockdemo.util.launchAndRepeatWithViewLifecycle
import com.eugeneosipenko.stockdemo.util.setChangeColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val company = args.company
        val profile = requireNotNull(company.profile)

        with(binding) {
            price.text = company.price.toString()
            name.text = company.name
            symbol.text = company.symbol
            changes.text = profile.changes.toString()
            lastDiv.text = profile.lastDiv.toString()
            sector.text = profile.sector
            industry.text = profile.industry
            image.load(profile.image)
            changes.setChangeColor(profile.changes)
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.realtimePrice(company.symbol).collect { binding.price.text = it.toString() }
            }
        }
    }
}
