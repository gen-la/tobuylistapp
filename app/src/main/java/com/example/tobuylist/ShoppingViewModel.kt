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

    fun deleteItem(id: Int) {
        val newList = mutableListOf<ShoppingItem>()

        for (item in items) {
            if (item.id != id) {
                newList.add(item)
            }
        }

        items = newList
    }

    fun toggleBought(id: Int) {
        val newList = mutableListOf<ShoppingItem>()

        for (item in items) {
            if (item.id == id) {
                val changedItem = item.copy(isBought = !item.isBought)
                newList.add(changedItem)
            } else {
                newList.add(item)
            }
        }

        items = newList
    }

    fun editItem(id: Int, newName: String, newSection: String) {
        val trimmedName = newName.trim()
        if (trimmedName.isEmpty()) return

        val newList = mutableListOf<ShoppingItem>()

        for (item in items) {
            if (item.id == id) {
                val changedItem = item.copy(name = trimmedName, section = newSection)
                newList.add(changedItem)
            } else {
                newList.add(item)
            }
        }

        items = newList
    }
}