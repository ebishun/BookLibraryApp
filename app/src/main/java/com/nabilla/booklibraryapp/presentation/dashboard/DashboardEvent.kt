package com.nabilla.booklibraryapp.presentation.dashboard

/**
 * A sealed class to handle event from Dashboard screen.
 * From screen, event will be sent to viewmodel.
 */
sealed class DashboardEvent {
    data class EnteredQuery(val value: String): DashboardEvent()
    object SearchBook: DashboardEvent()
    object ClearQuery: DashboardEvent()


}