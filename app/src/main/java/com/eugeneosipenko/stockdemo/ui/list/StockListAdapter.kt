package com.eugeneosipenko.stockdemo.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eugeneosipenko.stockdemo.databinding.ItemCompanyListBinding
import com.eugeneosipenko.stockdemo.model.Company

class StockListAdapter : ListAdapter<Company, CompanyItemViewHolder>(CompanyDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyItemViewHolder {
        return CompanyItemViewHolder(
            ItemCompanyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CompanyItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            name.text = item.name
            price.text = item.price.toString()
            exchange.text = item.exchange
            symbol.text = item.symbol
        }
    }
}

class CompanyItemViewHolder(
    internal val binding: ItemCompanyListBinding
) : RecyclerView.ViewHolder(binding.root)

object CompanyDiff : DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        // We don't have to compare the userEvent id because it matches the session id.
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }
}
