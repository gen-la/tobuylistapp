package com.example.tobuylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(viewModel: ShoppingViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Shopping List") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AddItemRow(onAdd = { name, section -> viewModel.addItem(name, section) })
        }
    }
}

val sections = listOf("Fruit & Veg", "Dairy", "Meat & Fish", "Bakery", "Frozen", "Drinks", "Other")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemRow(onAdd: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var section by remember { mutableStateOf(sections[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Item to buy") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = section,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Section") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    for (option in sections) {
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                section = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    onAdd(name, section)
                    name = ""
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add")
            }
        }
    }
}