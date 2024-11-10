package com.taposek322.shoppinglist.presentation.shop_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.taposek322.shoppinglist.domain.ShopItem
import com.taposek322.shoppinglist.presentation.root.RootComponent

interface ShopListComponent {
    val list: Value<List<ShopItem>>
}


class ShopListComponentImpl(
    componentContext: ComponentContext,
    shopList: List<ShopItem>
) : ShopListComponent {

    val _list = MutableValue(shopList)

    override val list: Value<List<ShopItem>>
        get() = _list

}