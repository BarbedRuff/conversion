package com.ok.conversion.data.api

import com.ok.conversion.data.model.Codes
import com.ok.conversion.data.model.Exchange
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{apiKey}/codes")
    fun getSupportedCodes(
        @Path("apiKey") apiKey: String
    ): Response<Codes>

    @GET("{apiKey}/pair/{baseCode}/{targetCode}/{amount}")
    fun getExchangeAmount(
        @Path("apiKey") apiKey: String,
        @Path("baseCode") baseCode: String,
        @Path("targetCode") targetCode: String,
        @Path("amount") amount: Int
    ): Response<Exchange>
}