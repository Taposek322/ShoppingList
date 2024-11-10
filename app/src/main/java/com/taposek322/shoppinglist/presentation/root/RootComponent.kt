package com.taposek322.shoppinglist.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.taposek322.shoppinglist.domain.ShopItem
import com.taposek322.shoppinglist.presentation.shop_form.ShopFormComponent
import com.taposek322.shoppinglist.presentation.shop_form.ShopFormComponentImpl
import com.taposek322.shoppinglist.presentation.shop_list.ShopListComponent
import com.taposek322.shoppinglist.presentation.shop_list.ShopListComponentImpl
import kotlinx.serialization.Serializable

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class FormChild(val component: ShopFormComponent): Child()
        class ListChild(val component: ShopListComponent): Child()
    }
}

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = nav,
            serializer= Config.serializer(),
            initialConfiguration = Config.ShopForm,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.ShopForm -> RootComponent.Child.FormChild(
                component = ShopFormComponentImpl(
                    componentContext = componentContext,
                    onFinishClicked = { shopList ->
                        nav.pushNew(Config.ShopList(shopList))
                    }
                )
            )
            is Config.ShopList -> RootComponent.Child.ListChild(
                component = ShopListComponentImpl(
                    componentContext = componentContext,
                    shopList = config.items
                )
            )
        }
    }


    @Serializable
    private sealed interface Config {
        @Serializable
        data object ShopForm: Config

        @Serializable
        data class ShopList(val items: List<ShopItem>) : Config
    }
}