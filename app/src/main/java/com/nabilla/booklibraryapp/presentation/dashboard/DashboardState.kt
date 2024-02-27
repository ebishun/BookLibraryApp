package com.nabilla.booklibraryapp.presentation.dashboard

import com.nabilla.booklibraryapp.domain.model.Book

data class DashboardState (
    val books: List<Book> = emptyList()
)