package com.taposek322.shoppinglist.presentation.shop_form

import com.arkivanov.decompose.ComponentContext
import com.taposek322.shoppinglist.domain.ShopItem
import kotlinx.serialization.Serializable

interface ShopFormComponent {
    fun onNextClick(name: String, price: Double, count: Int)
    fun onFinishClick()
    fun onShowAllClick()
}

class ShopFormComponentImpl(
    private val componentContext: ComponentContext,
    private val onFinishClicked: (List<ShopItem>) -> Unit
) : ShopFormComponent, ComponentContext by componentContext {
    private val shopListState : ShopListState = stateKeeper.consume(
        key = SAVED_STATE,
        strategy = ShopListState.serializer()
    ) ?: ShopListState()

    init {
        stateKeeper.register(
            key = SAVED_STATE,
            strategy = ShopListState.serializer()
        ) {
            shopListState
        }
    }
    override fun onNextClick(name: String, price: Double, count: Int) {
        shopListState.shopList.add(ShopItem(name, price, count))
    }

    override fun onFinishClick() {
        onFinishClicked(shopListState.shopList)
    }

    override fun onShowAllClick() {
        onFinishClicked(shopListState.shopList)
    }

    @Serializable
    private class ShopListState(val shopList: MutableList<ShopItem> = mutableListOf())

    companion object {
        const val SAVED_STATE = "ShopFormState"
    }
}