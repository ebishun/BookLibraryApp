package com.nabilla.booklibraryapp.domain.use_case

data class BookUseCases (
    val getBooks: GetBooks,
    val addBook: AddBook,
    val deleteBook: DeleteBook
)