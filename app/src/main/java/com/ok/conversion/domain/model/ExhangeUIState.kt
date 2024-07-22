package com.ok.conversion.domain.model

sealed class ExhangeUIState {
    object Init : ExhangeUIState()
    object Loading : ExhangeUIState()
    data class Succes(val exchange: Float) : ExhangeUIState()
    data class Error(val exception: String) : ExhangeUIState()
}