package com.example.softserve.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.softserve.CountryListQuery
import com.example.softserve.R
import com.example.softserve.databinding.CountyListFragmentBinding
import com.example.softserve.viewModel.CountryViewModel
import com.example.softserve.viewModel.ResultState

class CountryListFragment : Fragment() {

    private val viewModel: CountryViewModel by activityViewModels()

    private lateinit var binding: CountyListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CountyListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAndUpdateCountryList()

        viewModel.countryList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Loading -> {
                    binding.pbSpinner.visibility = View.VISIBLE
                    binding.tvNoIformation.visibility = View.GONE
                }
                is ResultState.Failure -> {
                    binding.pbSpinner.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        result.message,
                        Toast.LENGTH_LONG
                    ).show()
                    binding.tvNoIformation.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    binding.pbSpinner.visibility = View.GONE
                    binding.tvNoIformation.visibility = View.GONE
                    setUpRecyclerView(result.value)
                }
                else -> {
                    binding.pbSpinner.visibility = View.GONE
                    binding.tvNoIformation.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpRecyclerView(data: List<CountryListQuery.Country>) {
        binding.rvCountries.layoutManager = LinearLayoutManager(requireContext())

        val adapter = CountryAdapter(data) {
            viewModel.fetchAndUpdateCountry(it.code)
            navigateToDetails()
        }

        binding.rvCountries.adapter = adapter
    }

    private fun navigateToDetails() {
        findNavController().navigate(R.id.action_countryListFragment_to_countryFragment)
    }
}