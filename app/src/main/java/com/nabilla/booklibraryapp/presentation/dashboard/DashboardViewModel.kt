package com.nabilla.booklibraryapp.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nabilla.booklibraryapp.domain.use_case.BookUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val bookUseCases: BookUseCases
) :ViewModel() {

    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state
    val bookList = bookUseCases.getBooks()

    init {
        getBooks()
    }

    fun onEvent(event: DashboardEvent) {

    }

    private fun getBooks() {
        val myBooks = bookUseCases.getBooks()
    }

}