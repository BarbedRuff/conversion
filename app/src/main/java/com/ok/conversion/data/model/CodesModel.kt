package com.ok.conversion.data.model

import com.google.gson.annotations.SerializedName

data class Codes(
    @SerializedName("result")
    val result: String,
    @SerializedName("supported_codes")
    val supportedCodes: List<List<String>>
)