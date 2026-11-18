package com.example.tobuylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ShoppingViewModel : ViewModel() {

    var items by mutableStateOf(listOf<ShoppingItem>())
        private set

    private var nextId = 0

    fun addItem(name: String, section: String) {
        val trimmedName = name.trim()
        if (trimmedName.isEmpty()) return

        val newItem = ShoppingItem(
            id = nextId++,
            name = trimmedName,
            section = section
        )
        items = items + newItem
    }
    // toggle, edit and delete functions come next
}