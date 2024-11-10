package com.taposek322.shoppinglist.presentation.shop_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.taposek322.shoppinglist.domain.ShopItem

@Composable
fun ShopListContent(
    shopListComponent: ShopListComponent,
    modifier: Modifier = Modifier
) {
    val list by shopListComponent.list.subscribeAsState()
    ShopListScreen(
        shopList = list,
        modifier = modifier
    )
}

@Composable
fun ShopListScreen(
    shopList: List<ShopItem>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(24.dp)
        ) {
            items(shopList) {
                ShopItemContainer(
                    item = it,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ShopItemContainer(
    item: ShopItem,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = "Product name: ${item.name}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(
            modifier = Modifier
            .height(8.dp)
        )
        Text(
            text = "Price for one: ${item.price}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Text(
            text = "Count: ${item.count}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}