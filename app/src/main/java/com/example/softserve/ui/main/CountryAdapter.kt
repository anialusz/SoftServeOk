package com.example.softserve.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.softserve.data.Country
import com.example.softserve.databinding.ItemCountryListBinding

class CountryAdapter(
    private val dataSet: List<Country>,
    private val onClick: (Country) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemCountryListBinding,
        val onClick: (Country) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country) {
            binding.tvCountryValue.text = item.country
            binding.tvCapitalValue.text = item.capital
            binding.tvRegionValue.text = item.region

            binding.cvContainer.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCountryListBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}

