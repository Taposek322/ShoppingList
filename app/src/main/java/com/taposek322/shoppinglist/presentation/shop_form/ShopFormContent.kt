package com.taposek322.shoppinglist.presentation.shop_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ShopFormContent(
    component: ShopFormComponent,
    modifier: Modifier = Modifier
) {
    ShopFormScreen(
        onNextClick = component::onNextClick,
        onFinishClick = component::onFinishClick
    )
}

@Composable
fun ShopFormScreen(
    onNextClick: (String, Double, Int) -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var productName by rememberSaveable {
        mutableStateOf("")
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }

    var count by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        InputField(
            text = "Product name:",
            value = productName,
            onValueChanged = { productName = it },
            keyboardType = KeyboardType.Text
        )
        InputField(
            text = "Price:",
            value = price,
            onValueChanged = { price = it },
            keyboardType = KeyboardType.Decimal
        )
        InputField(
            text = "Count:",
            value = count,
            onValueChanged = { count = it },
            keyboardType = KeyboardType.Number
        )
        Button(
            onClick = {
                onNextClick(productName, price.toDouble(), count.toInt())
                productName = ""
                price = ""
                count = ""
            }
        ) {
            Text("Next item")
        }
        Button(
            onClick = { onFinishClick() }
        ) {
            Text("Finish shopping")
        }
    }
}

@Composable
fun InputField(
    text: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
    ) {
        Text(
            text = text
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
        )
        TextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}