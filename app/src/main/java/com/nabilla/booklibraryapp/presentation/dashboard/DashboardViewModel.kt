package com.nabilla.booklibraryapp.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.use_case.BookUseCases
import com.nabilla.booklibraryapp.presentation.add_edit_book.AddEditBookEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel handling Dashboard screen login.
 * In this case, only provides a list of book to display in Dashboard
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val bookUseCases: BookUseCases
) :ViewModel() {

    var _searchTitle = mutableStateOf("")
    val searchTitle =  _searchTitle

    private val _listOfBook = MutableSharedFlow<List<Book>>()
    val listOfBook = _listOfBook.asSharedFlow()

    init {
        viewModelScope.launch {
            _listOfBook.emit(getBooksByQuery(""))
        }
    }

    fun onEvent(event: DashboardEvent) {
        when(event){
            is DashboardEvent.EnteredQuery -> {
                _searchTitle.value =  event.value
                if (searchTitle.value.isEmpty()){
                    viewModelScope.launch {
                        _listOfBook.emit(getBooksByQuery(""))
                    }

                }
            }

            DashboardEvent.SearchBook -> {
                viewModelScope.launch {
                    val myResultQuery = getBooksByQuery(searchTitle.value)
                    if (myResultQuery.isNotEmpty()){
                        _listOfBook.emit(myResultQuery)
                    }

                }
            }

            DashboardEvent.ClearQuery -> _searchTitle.value = ""
        }
    }

    fun getBooks():List<Book>{
        return bookUseCases.getBooks()
    }

    fun getBooksByQuery(strQuery:String):List<Book>{
        return bookUseCases.filterBookQuery(strQuery)
    }

}