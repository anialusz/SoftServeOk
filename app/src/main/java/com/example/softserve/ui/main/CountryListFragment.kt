package com.example.softserve.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.softserve.R
import com.example.softserve.data.Country
import com.example.softserve.databinding.CountyListFragmentBinding

class CountryListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

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

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvCountries.layoutManager = LinearLayoutManager(requireContext())

        val adapter = CountryAdapter(listOf(Country("TEST", "da", "daa"))) {
            navigateToDetails()
        }

        binding.rvCountries.adapter = adapter
    }

    private fun navigateToDetails() {
        findNavController().navigate(R.id.action_countryListFragment_to_countryFragment)
    }
}