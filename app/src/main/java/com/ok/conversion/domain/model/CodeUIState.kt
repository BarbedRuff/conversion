package com.ok.conversion.domain.model

sealed class CodeUIState {
    object Init : CodeUIState()
    object Loading : CodeUIState()
    data class Succes(val supportedCodes: List<List<String>>) : CodeUIState()
    data class Error(val exception: String) : CodeUIState()
}