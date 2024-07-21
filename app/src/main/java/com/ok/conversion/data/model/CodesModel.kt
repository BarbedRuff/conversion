package com.ok.conversion.data.model

import com.google.gson.annotations.SerializedName

data class Codes(
    @SerializedName("result")
    val result: String,
    @SerializedName("supported_codes")
    val supportedCodes: List<Code>
)

data class Code(
    val code: String,
    val description: String
)