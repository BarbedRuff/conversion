package com.ok.conversion.data.repository

import com.ok.conversion.data.api.ApiService
import com.ok.conversion.domain.model.CodeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception
import javax.inject.Inject

class CodesRepository @Inject constructor(private val apiService: ApiService) {
    private val _codes = MutableStateFlow<CodeUIState>(CodeUIState.Init)
    var codes = _codes.asStateFlow()

    suspend fun getCodes(){
        _codes.value = CodeUIState.Loading
        try{
            val response = apiService.getSupportedCodes()
            _codes.value = when (response.code()) {
                200 -> CodeUIState.Succes(response.body()!!.supportedCodes)
                else -> CodeUIState.Error("Network error")
            }
        } catch (e : Exception){
            _codes.value = CodeUIState.Error("Network error")
        }
    }
}