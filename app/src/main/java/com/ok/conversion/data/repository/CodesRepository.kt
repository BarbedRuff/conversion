package com.ok.conversion.data.repository

import com.ok.conversion.data.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CodesRepository @Inject constructor(private val apiService: ApiService) {
    private val _codes = MutableStateFlow<List<List<String>>>(listOf())
    var codes = _codes.asStateFlow()

    suspend fun getCodes(){
        val response = apiService.getSupportedCodes()
        if(response.code() == 200 && response.body()?.result == "success"){
            _codes.value = response.body()!!.supportedCodes
        }
    }
}