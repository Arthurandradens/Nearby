package com.nlw.nearby.ui.screen.market_details

import com.nlw.nearby.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
)
