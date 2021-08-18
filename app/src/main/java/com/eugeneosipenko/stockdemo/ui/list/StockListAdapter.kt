package com.eugeneosipenko.stockdemo.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.clear
import coil.load
import com.eugeneosipenko.stockdemo.R
import com.eugeneosipenko.stockdemo.databinding.ItemCompanyListBinding
import com.eugeneosipenko.stockdemo.model.Company
import com.eugeneosipenko.stockdemo.util.setChangeColor

class StockListAdapter(
    private val delegate: StockListViewModelDelegate
) : ListAdapter<Company, CompanyItemViewHolder>(CompanyDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyItemViewHolder {
        return CompanyItemViewHolder(
            ItemCompanyListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            delegate
        )
    }

    override fun onBindViewHolder(holder: CompanyItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CompanyItemViewHolder(
    private val binding: ItemCompanyListBinding,
    private val delegate: StockListViewModelDelegate
) : RecyclerView.ViewHolder(binding.root) {

    private var company: Company? = null

    init {
        binding.container.setOnClickListener {
            Log.e("ZXC", "CLICK")
            company?.let { delegate.openCompanyDetails(it) }
        }
    }

    fun bind(company: Company) {
        this.company = company

        with(binding) {
            name.text = company.name
            price.text = company.price.toString()
            exchange.text = company.exchange
            symbol.text = company.symbol

            price.setTextColor(android.graphics.Color.BLACK)
            image.setImageDrawable(null)
            image.clear()

            company.profile?.let {
                image.load(it.image)
                price.setChangeColor(it.changes)
            }
        }

        delegate.loadCompanyProfile(company.symbol)
    }
}

object CompanyDiff : DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        // We don't have to compare the userEvent id because it matches the session id.
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }
}
