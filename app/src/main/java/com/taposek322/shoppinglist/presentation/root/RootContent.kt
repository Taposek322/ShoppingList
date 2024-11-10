package com.taposek322.shoppinglist.presentation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.taposek322.shoppinglist.presentation.root.RootComponent.Child
import com.taposek322.shoppinglist.presentation.shop_form.ShopFormContent
import com.taposek322.shoppinglist.presentation.shop_list.ShopListContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Child(
        childStack = component.stack,
    )
}

@Composable
fun Child(
    childStack: Value<ChildStack<*, Child>>,
    modifier: Modifier = Modifier
) {
    Children(
        stack = childStack,
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val child = it.instance) {
                is Child.FormChild -> ShopFormContent(child.component)
                is Child.ListChild -> ShopListContent(child.component)
            }
        }
    }
}