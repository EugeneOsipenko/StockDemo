package com.eugeneosipenko.stockdemo.domain

import com.eugeneosipenko.stockdemo.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class RealtimePriceUseCase @Inject constructor(
    private val repository: RealtimePriceRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Double>(dispatcher) {

    override suspend fun execute(parameters: String): Double {
        return repository.realtimePrice(parameters)
    }
}
