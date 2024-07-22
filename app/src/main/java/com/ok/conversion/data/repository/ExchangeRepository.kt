package com.ok.conversion.data.repository

import com.ok.conversion.data.api.ApiService
import com.ok.conversion.domain.model.ExhangeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception
import javax.inject.Inject

class ExchangeRepository @Inject constructor(private val apiService: ApiService) {
    private val _exchange = MutableStateFlow<ExhangeUIState>(ExhangeUIState.Init)
    var exchange: StateFlow<ExhangeUIState> = _exchange.asStateFlow()

    suspend fun getExchangeAmount(
        baseCode: String,
        targetCode: String,
        amount: Float
    ) {
        _exchange.value = ExhangeUIState.Loading
        try{
            val response = apiService.getExchangeAmount(baseCode, targetCode, amount)
            _exchange.value = when (response.code()) {
                200 -> ExhangeUIState.Succes(response.body()!!.conversionResult)
                else -> ExhangeUIState.Error("Network error")
            }
        } catch (e : Exception){
            _exchange.value = ExhangeUIState.Error("Network error")
        }
    }
}