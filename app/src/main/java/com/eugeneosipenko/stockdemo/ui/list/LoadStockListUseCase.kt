package com.eugeneosipenko.stockdemo.ui.list

import UseCase
import com.eugeneosipenko.stockdemo.di.IoDispatcher
import com.eugeneosipenko.stockdemo.model.Company
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class LoadStockListUseCase @Inject constructor(
    private val repository: StockListRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<Company>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<Company> {
        return repository.loadCompaniesList()
    }
}
