package com.ok.conversion.data.api

import com.ok.conversion.data.model.Codes
import com.ok.conversion.data.model.Exchange
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/codes")
    suspend fun getSupportedCodes(): Response<Codes>

    @GET("/pair/{baseCode}/{targetCode}/{amount}")
    suspend fun getExchangeAmount(
        @Path("baseCode") baseCode: String,
        @Path("targetCode") targetCode: String,
        @Path("amount") amount: Float
    ): Response<Exchange>
}