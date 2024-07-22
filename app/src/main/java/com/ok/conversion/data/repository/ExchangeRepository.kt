package com.ok.conversion.data.repository

import com.ok.conversion.data.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ExchangeRepository @Inject constructor(private val apiService: ApiService) {
    private val _exchange = MutableStateFlow(0f)
    var exchange = _exchange.asStateFlow()

    suspend fun getExchangeAmount(
        baseCode: String,
        targetCode: String,
        amount: Float
    ){
        val response = apiService.getExchangeAmount(baseCode, targetCode, amount)
        if(response.code() == 200 && response.body()?.result == "success"){
            _exchange.value = response.body()!!.conversionResult
        }
    }
}