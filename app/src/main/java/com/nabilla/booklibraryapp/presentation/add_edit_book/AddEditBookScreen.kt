package com.nabilla.booklibraryapp.presentation.add_edit_book

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nabilla.booklibraryapp.presentation.add_edit_book.components.DropdownTextField
import com.nabilla.booklibraryapp.ui.theme.DarkRed

@Composable
fun AddEditBookScreen(
    navController: NavController,
    bookId:Int,
    viewModel: AddEditBookViewModel = hiltViewModel()
) {

    val bookTitle = viewModel.bookTitle.value
    val bookAuthor = viewModel.bookAuthor.value
    val bookGenre = viewModel.bookGenre.value
    val bookLanguage = viewModel.bookLanguage.value
    val bookSummary = viewModel.bookSummary.value

    // Show toast message for invalid input
    val context = LocalContext.current
    var isSubmitSuccess = true

    val genres = listOf("Fiction", "Non-Fiction", "Historical Fiction", "Asian", "Biography","Comedy","Manga")
    var selectedGenre by remember { mutableStateOf(bookGenre) }

    LaunchedEffect(true) {
        viewModel.invalidInputMessage.collect { message ->
            isSubmitSuccess = if(message.isNotEmpty()) {
                showToast(context,message)
                false
            }else{
                true
            }
        }
    }

    Scaffold(){ paddingValues->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = bookTitle,
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredTitle(it))
                },
                label = { Text("Title") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = bookAuthor,
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredAuthor(it))
                },
                label = { Text("Author") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            DropdownTextField(
                options = genres,
                selectedOption = selectedGenre,
                onOptionSelected = {
                    selectedGenre = it
                    viewModel.onEvent(AddEditBookEvent.EnteredGenre(selectedGenre))
                }
            )

            /*
            OutlinedTextField(
                value = bookGenre,
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredGenre(selectedGenre))
                    //viewModel.onEvent(AddEditBookEvent.EnteredGenre(it))
                },
                label = { Text("Genre") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                readOnly = true,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable { },
            )
            */

            OutlinedTextField(
                value = bookLanguage,
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredLanguage(it))
                },
                label = { Text("Language") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = bookSummary,
                onValueChange = {
                    viewModel.onEvent(AddEditBookEvent.EnteredSummary(it))},
                label = { Text("Summary") },
                maxLines = 10,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)

            )

            Button(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = DarkRed
                ),
                onClick = {
                    viewModel.onEvent(AddEditBookEvent.DeleteBook)
                    if (isSubmitSuccess){
                        navController.popBackStack()
                    }
                }
            ) {

                Text("Delete Book")
            }

            Button(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                onClick = {
                // Perform task
                // Navigate back to dashboard screen
                    viewModel.onEvent(AddEditBookEvent.SaveBook)
                    if (isSubmitSuccess){
                        navController.popBackStack()
                    }
                }
            ) {

                Text("Save Book")
            }
        }
    }

}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}