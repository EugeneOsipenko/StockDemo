package com.eugeneosipenko.stockdemo.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugeneosipenko.stockdemo.domain.LoadCompanyProfileUseCase
import com.eugeneosipenko.stockdemo.domain.LoadStockListUseCase
import com.eugeneosipenko.stockdemo.model.Company
import com.eugeneosipenko.stockdemo.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val DEBOUNCE_TIME = 300L

interface StockListViewModelDelegate {
    fun loadCompanyProfile(symbol: String)

    fun openCompanyDetails(company: Company)
}

@HiltViewModel
class StockListViewModel @Inject constructor(
    loadStockListUseCase: LoadStockListUseCase,
    private val loadCompanyProfileUseCase: LoadCompanyProfileUseCase
) : ViewModel(), StockListViewModelDelegate {

    private val allCompanies = mutableMapOf<String, Company>()
    private val companiesFlow = MutableStateFlow<List<Company>>(emptyList())
    private val navigationFlow = MutableSharedFlow<Company>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val companies: Flow<List<Company>> = companiesFlow
        .onStart { emitAll(initialLoad) }
        .filter { it.isNotEmpty() }
        .stateIn(viewModelScope, WhileViewSubscribed, emptyList())
        .debounce(DEBOUNCE_TIME)

    val openCompanyDetails: Flow<Company> = flow {
        emitAll(navigationFlow)
    }

    private val initialLoad: Flow<List<Company>> = flow {
        loadStockListUseCase(Unit)
            .getOrElse { emptyList() }
            .apply { emit(this) }
            .associateByTo(allCompanies) { it.symbol }
    }

    override fun loadCompanyProfile(symbol: String) {
        if (allCompanies[symbol]?.profile != null) return

        viewModelScope.launch {
            val companyProfile = loadCompanyProfileUseCase(symbol).getOrNull() ?: return@launch
            val company = allCompanies.getValue(companyProfile.symbol)

            allCompanies[companyProfile.symbol] = company.copy(profile = companyProfile)
            companiesFlow.emit(allCompanies.values.toList())
        }
    }

    override fun openCompanyDetails(company: Company) {
        navigationFlow.tryEmit(company)
    }
}
