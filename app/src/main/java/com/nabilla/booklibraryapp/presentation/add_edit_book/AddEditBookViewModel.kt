package com.nabilla.booklibraryapp.presentation.add_edit_book

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabilla.booklibraryapp.R
import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.use_case.BookUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel handling AddEditBook screen.
 * Add, Edit, Delete book will be handled here
 * Listen to the screen event and execute respective event
 * Return the result to screen
 * Has an argument of bookId to display clicked book
 * If value is -1, then assume book doesn't exist, and will instead Add book
 *
 */
@HiltViewModel
class AddEditBookViewModel @Inject constructor(
    private val bookUseCases: BookUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val TAG = "AddEditBookViewModel"
    var currentBookId = -1
    lateinit var myBook:Book
    var bookSize = bookUseCases.getBooks

    var _bookTitle = mutableStateOf("")
    val bookTitle =  _bookTitle

    var _bookAuthor = mutableStateOf("")
    val bookAuthor =  _bookAuthor

    var _bookGenre = mutableStateOf("")
    val bookGenre =  _bookGenre

    var _bookLanguage = mutableStateOf("")
    val bookLanguage =  _bookLanguage

    var _bookSummary = mutableStateOf("")
    val bookSummary =  _bookSummary

    var _bookResId = mutableStateOf(R.drawable.book6)
    val bookResId =  _bookResId

    private val _invalidInputMessage = MutableSharedFlow<String>()
    val invalidInputMessage = _invalidInputMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            _invalidInputMessage.emit("")
        }

        savedStateHandle.get<Int>("bookId")?.let { bookId ->
            Log.e(TAG, "bookId: $bookId")
            if(bookId != -1) {
                myBook = bookUseCases.getBook(bookId-1).also { book ->
                    currentBookId = book.id
                    _bookTitle.value = book.bookTitle
                    _bookSummary.value = book.bookSummary
                    _bookAuthor.value = book.bookAuthor
                    _bookGenre.value = book.bookGenre
                    _bookLanguage.value = book.bookLanguage
                    _bookResId.value = book.bookResId
                }
            }
        }
    }

    fun onEvent(event: AddEditBookEvent) {
        when(event){
            is AddEditBookEvent.EnteredTitle -> _bookTitle.value = event.value
            is AddEditBookEvent.EnteredSummary -> _bookSummary.value = event.value
            is AddEditBookEvent.EnteredAuthor -> _bookAuthor.value = event.value
            is AddEditBookEvent.EnteredGenre -> _bookGenre.value = event.value
            is AddEditBookEvent.EnteredLanguage -> _bookLanguage.value = event.value

            is AddEditBookEvent.DeleteBook -> {
                if (currentBookId != -1){
                    deleteBook(myBook)
                }else{
                    viewModelScope.launch {
                        _invalidInputMessage.emit("Book does not exist")
                    }
                }
            }

            is AddEditBookEvent.SaveBook -> {
                if (bookTitle.value.isEmpty() || bookAuthor.value.isEmpty()){
                    viewModelScope.launch {
                        _invalidInputMessage.emit("Invalid input: Text field cannot be empty")
                    }

                    return
                }

                if(currentBookId != -1) {
                    val updateBook = Book(
                        currentBookId,
                        bookTitle.value,
                        bookAuthor.value,
                        bookSummary.value,
                        bookGenre.value,
                        bookLanguage.value,
                        bookResId.value
                        )
                    updateBook(currentBookId-1,updateBook)
                }else{
                    addBook(Book(
                        bookSize.invoke().size+1,
                        bookTitle.value,
                        bookAuthor.value,
                        bookSummary.value,
                        bookGenre.value,
                        bookLanguage.value,
                        bookResId.value
                    )
                    )
                }
            }
        }
    }


    private fun addBook(book: Book){
        bookUseCases.addBook(book)
        Log.e("AddEditViewModel","Add Book: ${bookUseCases.getBooks().size}")
    }

    private fun deleteBook(book: Book){
        bookUseCases.deleteBook(book)
    }

    private fun updateBook(id:Int, book: Book){
        bookUseCases.updateBook(id, book)
    }
}