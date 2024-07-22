package com.ok.conversion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ok.conversion.data.repository.CodesRepository
import com.ok.conversion.data.repository.ExchangeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val codesRepository: CodesRepository,
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {
    val codes = codesRepository.codes
    val exchange = exchangeRepository.exchange
    init{
        viewModelScope.launch {
            codesRepository.getCodes()
        }
    }
    fun getExchangeAmount(baseCode: String, targetCode: String, amount: Float){
        viewModelScope.launch {
            exchangeRepository.getExchangeAmount(baseCode, targetCode, amount)
        }
    }
}