package com.nabilla.booklibraryapp.presentation.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nabilla.booklibraryapp.presentation.dashboard.components.BookCard

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    //val state = viewModel.state.value
    val bookList = viewModel.bookList
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //viewModel.onEvent(ShoppingListEvent.OnAddShoppingItemClick)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(bookList.size) { bookItem ->
                BookCard(
                    book = bookList[bookItem]
                )
            }
        }
    }
}