package com.nabilla.booklibraryapp.domain.use_case

import com.nabilla.booklibraryapp.domain.model.Book
import com.nabilla.booklibraryapp.domain.repository.BookRepository

/**
 * A usecase class to retrieve a list of book from repository
 */
class GetBooks(private val repository: BookRepository) {
    operator fun invoke():List<Book> {
        return repository.getBooks()
    }

}