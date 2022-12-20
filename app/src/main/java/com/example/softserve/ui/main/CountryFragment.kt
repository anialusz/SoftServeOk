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
import com.example.softserve.databinding.CountyFragmentBinding
import com.example.softserve.databinding.CountyListFragmentBinding

class CountryFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

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
    }


}