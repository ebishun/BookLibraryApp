package com.nabilla.booklibraryapp.presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nabilla.booklibraryapp.R
import com.nabilla.booklibraryapp.domain.util.Routes
import com.nabilla.booklibraryapp.presentation.dashboard.components.BookCard

/**
 * A composable function for Dashboard screen to display a list of book.
 * Contains scaffold, for FloatingActionButton and AppBar
 * List is displayed using LazyColumn
 * FloatingActionButton onClick will navigate to AddEditBookScreen, with bookId as an argument
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val bookQuery = viewModel.searchTitle.value
    val bookList = viewModel.listOfBook.collectAsState(initial = viewModel.getBooksByQuery(""))

    /*
    LaunchedEffect(true) {
        //Log.d("DashboardScreen","bookSize: ${bookList.value.size}")
        /*
        viewModel.listOfBook.collect { book ->
            Log.d("DashboardScreen","bookSize: ${book.size}")
            bookList = book
        }
        */
    }
    */

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.ADD_EDIT_BOOK)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Book Library App",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
            )
        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
        ) {
            OutlinedTextField(
                value = bookQuery,
                onValueChange = {
                    viewModel.onEvent(DashboardEvent.EnteredQuery(it))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                label = { Text("Search Book Title") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(DashboardEvent.SearchBook)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = Color.Black
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier.padding(5.dp)
            ) {
                items(bookList.value.size) { idx ->
                    val bookItem = bookList.value[idx]
                    BookCard(
                        book = bookItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                viewModel.onEvent(DashboardEvent.ClearQuery)
                                navController.navigate(
                                    Routes.ADD_EDIT_BOOK +
                                            "?bookId=${bookItem.id}"
                                )

                                /*
                                navController.navigate(
                                    Routes.ADD_EDIT_BOOK +
                                            "?bookId=${idx}"
                                )
                                */
                            },
                        imageResId = bookItem.bookResId
                    )
                }
            }
        }


    }
}