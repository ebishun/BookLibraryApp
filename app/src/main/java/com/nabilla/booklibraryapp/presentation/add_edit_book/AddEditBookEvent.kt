package com.nabilla.booklibraryapp.presentation.add_edit_book

/**
 * A sealed class to handle event from AddEdit screen.
 * From screen, event will be sent to viewmodel.
 */
sealed class AddEditBookEvent {
    data class EnteredTitle(val value: String): AddEditBookEvent()
    data class EnteredAuthor(val value: String): AddEditBookEvent()
    data class EnteredGenre(val value: String): AddEditBookEvent()
    data class EnteredLanguage(val value: String): AddEditBookEvent()
    data class EnteredSummary(val value: String): AddEditBookEvent()
    object SaveBook: AddEditBookEvent()
    object DeleteBook: AddEditBookEvent()
}