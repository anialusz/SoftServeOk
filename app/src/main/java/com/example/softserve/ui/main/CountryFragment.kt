package com.example.softserve.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.softserve.CountryQuery
import com.example.softserve.R
import com.example.softserve.databinding.CountyFragmentBinding
import com.example.softserve.viewModel.CountryViewModel
import com.example.softserve.viewModel.ResultState

class CountryFragment : Fragment() {

    private val viewModel: CountryViewModel by activityViewModels()

    private lateinit var binding: CountyFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CountyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.country.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Loading -> {
                    binding.pbSpinner.visibility = View.VISIBLE
                }
                is ResultState.Failure -> {
                    binding.pbSpinner.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        result.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ResultState.Success -> {
                    setupData(result.value)
                    binding.pbSpinner.visibility = View.GONE
                }
                else -> {
                    binding.pbSpinner.visibility = View.GONE
                }
            }
        }
    }

    private fun setupData(country: CountryQuery.Country) {
        binding.tvFlag.text = country.emoji

        binding.lCountry.tvLabel.text = getString(R.string.country)
        binding.lCountry.tvValue.text = country.name

        binding.lCapital.ivIcon.background =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_filled, null)
        binding.lCapital.tvLabel.text = getString(R.string.capital)
        binding.lCapital.tvValue.text = country.capital

        binding.lRegion.tvLabel.text = getString(R.string.region)
        binding.lRegion.tvValue.text = country.continent.name

        binding.lPopulation.tvLabel.text = getString(R.string.population)
        binding.lPopulation.tvValue.text = getString(R.string.tbd)

        binding.lLanguages.ivIcon.background =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_filled, null)
        binding.lLanguages.tvLabel.text = getString(R.string.languages)
        binding.lLanguages.tvValue.text = country.languages.map {
            it.name
        }.joinToString(", ")

        binding.lCurrencies.tvLabel.text = getString(R.string.currencies)
        binding.lCurrencies.tvValue.text = country.currency

        binding.lCallingCodes.tvLabel.text = getString(R.string.calling_codes)
        binding.lCallingCodes.tvValue.text = country.phone
    }
}