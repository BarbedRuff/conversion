package com.ok.conversion.data.model

import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("result")
    val result: String,
    @SerializedName("conversion_result")
    val conversionResult: Int
)