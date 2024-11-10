package com.taposek322.shoppinglist.domain

import kotlinx.serialization.Serializable

@Serializable
data class ShopItem(
    val name: String,
    val price: Double,
    val count: Int
)
