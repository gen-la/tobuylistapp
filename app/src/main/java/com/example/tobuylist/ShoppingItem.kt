package com.example.tobuylist

data class ShoppingItem(
    val id: Int,
    val name: String,
    val section: String,
    val isBought: Boolean = false
)