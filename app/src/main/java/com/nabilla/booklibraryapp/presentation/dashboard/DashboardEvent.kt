package com.nabilla.booklibraryapp.presentation.dashboard

import com.nabilla.booklibraryapp.domain.model.Book

sealed class DashboardEvent {

    data class DeleteBook(val book: Book): DashboardEvent()
}