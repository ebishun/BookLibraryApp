package com.nabilla.booklibraryapp.domain.use_case

/**
 * Compile the usecases classes into data class
 * Easier for DI
 * Hilt only provides 1 data class instead of multiple classes
 * Shared between viewmodels
 */
data class BookUseCases (
    val getBooks: GetBooks,
    val getBook: GetBook,
    val addBook: AddBook,
    val updateBook: UpdateBook,
    val deleteBook: DeleteBook,
    val filterBookQuery: FilterBookQuery
)