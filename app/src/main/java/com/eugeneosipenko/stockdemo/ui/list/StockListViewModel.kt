package com.eugeneosipenko.stockdemo.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugeneosipenko.stockdemo.model.Company
import com.eugeneosipenko.stockdemo.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val loadStockListUseCase: LoadStockListUseCase
) : ViewModel() {

    val companies: StateFlow<List<Company>> = flow {
        val agendaData = loadStockListUseCase(Unit).getOrNull() ?: emptyList()
        emit(agendaData)
    }.stateIn(viewModelScope, WhileViewSubscribed, initialValue = emptyList())
}
