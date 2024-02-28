package com.nabilla.booklibraryapp.presentation.dashboard

sealed class DashboardEvent {

    data class EnteredQuery(val value: String): DashboardEvent()
    object SearchBook: DashboardEvent()
    object ClearQuery: DashboardEvent()


}