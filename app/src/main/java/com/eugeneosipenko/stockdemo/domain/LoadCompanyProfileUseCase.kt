package com.eugeneosipenko.stockdemo.domain

import com.eugeneosipenko.stockdemo.di.IoDispatcher
import com.eugeneosipenko.stockdemo.model.CompanyProfile
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class LoadCompanyProfileUseCase @Inject constructor(
    private val repository: StockListRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, CompanyProfile>(dispatcher) {

    override suspend fun execute(parameters: String): CompanyProfile {
        return repository.loadCompanyProfile(parameters)
    }
}
